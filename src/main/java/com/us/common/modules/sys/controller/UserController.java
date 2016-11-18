package com.us.common.modules.sys.controller;

import com.google.common.collect.Lists;
import com.us.common.modules.sys.entities.Role;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.service.SystemService;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.utils.StringUtil;
import com.us.spring.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jansing on 16-11-8.
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
    @Autowired
    private SystemService systemService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? systemService.getUser(id) : new User();
    }

    @RequestMapping(value = {"list", ""})
    public String list(User user, HttpServletRequest req, HttpServletResponse resp, Model model) {
        Page<User> page = systemService.findUser(new Page<>(req, resp), user);
        model.addAttribute("page", page);
        return "modules/sys/userList";
    }

    @RequestMapping(value = "form")
    public String form(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", this.systemService.findAllRole());
        return "modules/sys/user/form";
    }

    @RequestMapping(value = "save")
    public String save(User user, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtil.isNotBlank(user.getPassword())) {
            user.setPassword(SystemService.encryptPassword(user.getPassword()));
        }

        if (!this.beanValidator(model, user, new Class[0])) {
            return this.form(user, model);
        }
        if (!"true".equals(this.checkUsername(user))) {
            this.addMessage(model, new String[]{"保存用户\'" + user.getUsername() + "\'失败，登录名已存在"});
            return this.form(user, model);
        }
        List<Role> roleList = Lists.newArrayList();
        List<String> roleIdList = user.getRoleIdList();
        Iterator<Role> roleIterator = this.systemService.findAllRole().iterator();

        while (roleIterator.hasNext()) {
            Role r = roleIterator.next();
            if (roleIdList.contains(r.getId())) {
                roleList.add(r);
            }
        }

        user.setRoleList(roleList);
        this.systemService.saveUser(user);

        //如果修改的是当前用户，则清除缓存
        if (user.getUsername().equals(UserUtil.getUser().getUsername())) {
            UserUtil.clearCache();
        }

        this.addMessage(redirectAttributes, "保存用户\'" + user.getUsername() + "\'成功");
        return "redirect:" + this.adminPath + "/sys/user/list?repage";
    }

    @ResponseBody
    @RequestMapping({"checkUsername"})
    public String checkUsername(User user) {
        User tmpUser = systemService.getUserByUsername(user.getUsername());
        return (StringUtil.isBlank(user.getId()) && tmpUser == null) || user.getId().equals(tmpUser.getId()) ? "true" : "false";
    }

    @RequestMapping(value = "delete")
    public String delete(User user, RedirectAttributes redirectAttributes) {
        if (UserUtil.getUser().getId().equals(user.getId())) {
            this.addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
        } else if (UserUtil.isAdmin(user.getId())) {
            this.addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
        } else {
            this.systemService.deleteUser(user);
            this.addMessage(redirectAttributes, "删除用户成功");
        }
        return "redirect:" + this.adminPath + "/sys/user/?repage";
    }

    @RequestMapping(value = "disable")
    public String disable(User user, RedirectAttributes redirectAttributes) {
        systemService.disableUser(user);
        addMessage(redirectAttributes, "禁用用户成功");
        return "redirect:" + this.adminPath + "/sys/user/?repage";
    }

    @RequestMapping(value = "enable")
    public String enable(User user, RedirectAttributes redirectAttributes) {
        systemService.enableUser(user);
        addMessage(redirectAttributes, "启用用户成功");
        return "redirect:" + this.adminPath + "/sys/user/?repage";
    }
}
