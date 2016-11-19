package com.us.image.controller;

import com.us.common.persistence.Page;
import com.us.common.utils.StringUtil;
import com.us.image.entities.Rule;
import com.us.image.service.RuleService;
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
 * Created by jansing on 16-11-8.
 */
@Controller
@RequestMapping(value = "${adminPath}/rule")
public class RuleController extends BaseController {
    @Autowired
    private RuleService ruleService;

    @ModelAttribute
    public Rule get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? ruleService.get(id) : new Rule();
    }

    @RequiresPermissions({"dict:view"})
    @RequestMapping(value = {"list", ""})
    public String list(Rule rule, HttpServletRequest req, HttpServletResponse resp, Model model) {
        Page<Rule> page = ruleService.findPage(new Page<Rule>(req, resp), rule);
        model.addAttribute("page", page);
        return "modules/rule/list";
    }

    @RequiresPermissions({"dict:view"})
    @RequestMapping(value = "form")
    public String form(Rule rule, Model model) {
        model.addAttribute("rule", rule);
        return "modules/rule/form";
    }

    @RequiresPermissions({"dict:edit"})
    @RequestMapping(value = "save")
    public String save(Rule rule, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, rule)) {
            return form(rule, model);
        }
        ruleService.save(rule);
        addMessage(redirectAttributes, "保存规则成功");
        return "redirect:" + this.adminPath + "/rule/?repage";
    }

    @RequiresPermissions({"dict:edit"})
    @RequestMapping(value = "delete")
    public String delete(Rule rule, RedirectAttributes redirectAttributes) {
        ruleService.delete(rule);
        addMessage(redirectAttributes, "删除规则成功");
        return "redirect:" + this.adminPath + "/rule/?repage";
    }

    @RequiresPermissions({"dict:edit"})
    @RequestMapping(value = "disable")
    public String disable(Rule rule, RedirectAttributes redirectAttributes) {
        ruleService.disable(rule);
        addMessage(redirectAttributes, "禁用规则成功");
        return "redirect:" + this.adminPath + "/rule/?repage";
    }

    @RequiresPermissions({"dict:edit"})
    @RequestMapping(value = "enable")
    public String enable(Rule rule, RedirectAttributes redirectAttributes) {
        ruleService.enable(rule);
        addMessage(redirectAttributes, "启用规则成功");
        return "redirect:" + this.adminPath + "/rule/?repage";
    }
}
