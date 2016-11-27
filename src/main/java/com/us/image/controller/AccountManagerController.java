package com.us.image.controller;

import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.persistence.Page;
import com.us.common.utils.StringUtil;
import com.us.image.entities.Account;
import com.us.image.service.AccountService;
import com.us.spring.mvc.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jansing on 16-11-27.
 */
@Controller
@RequestMapping(value = "${adminPath}/account")
public class AccountManagerController extends BaseController {
    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public Account get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? accountService.selectById(id) : new Account();
    }

    @RequiresPermissions({"account:view"})
    @RequestMapping(value = {"list", ""})
    public String list(Account account, HttpServletRequest req, HttpServletResponse resp, Model model) {
        Page<Account> page = accountService.findPage(new Page<>(req, resp), account);
        model.addAttribute("page", page);
        return "modules/account/list";
    }

    @RequiresPermissions({"account:view"})
    @RequestMapping(value = "form")
    public String form(Account account, Model model) {
        model.addAttribute("account", account);
        return "modules/account/form";
    }

    @RequiresPermissions({"account:edit"})
    @RequestMapping(value = "delete")
    public String delete(Account account, RedirectAttributes redirectAttributes) {
        accountService.delete(account);
        addMessage(redirectAttributes, "删除帐号成功");
        return "redirect:" + this.adminPath + "/account/?repage";
    }

    @RequiresPermissions({"account:edit"})
    @RequestMapping(value = "disable")
    public String disable(Account account, RedirectAttributes redirectAttributes) {
        account.setLocked(Integer.valueOf(DictUtil.getDictValue("禁用", "valid", "")));
        accountService.updateLocked(account);
        addMessage(redirectAttributes, "禁用帐号成功");
        return "redirect:" + this.adminPath + "/account/?repage";
    }

    @RequiresPermissions({"account:edit"})
    @RequestMapping(value = "enable")
    public String enable(Account account, RedirectAttributes redirectAttributes) {
        account.setLocked(Integer.valueOf(DictUtil.getDictValue("正常", "valid", "")));
        accountService.updateLocked(account);
        addMessage(redirectAttributes, "启用帐号成功");
        return "redirect:" + this.adminPath + "/account/?repage";
    }
}
