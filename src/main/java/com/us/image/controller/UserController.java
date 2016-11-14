package com.us.image.controller;

import com.us.common.persistence.Page;
import com.us.common.utils.StringUtil;
import com.us.image.entities.User;
import com.us.image.service.UserService;
import com.us.spring.mvc.controller.BaseController;
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
@RequestMapping(value = "${adminPath}/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? userService.get(id) : new User();
    }

    @RequestMapping(value = {"list", ""})
    public String list(User user, HttpServletRequest req, HttpServletResponse resp, Model model) {
        Page<User> page = userService.findPage(new Page<User>(req, resp), user);
        model.addAttribute("page", page);
        return "modules/user/list";
    }

    @RequestMapping(value = "form")
    public String form(User user, Model model) {
        model.addAttribute("user", user);
        return "modules/user/form";
    }

    @RequestMapping(value = "save")
    public String save(User user, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, user)) {
            return form(user, model);
        }
        userService.save(user);
        addMessage(redirectAttributes, "保存用户成功");
        return "redirect:" + this.adminPath + "/user/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(User user, RedirectAttributes redirectAttributes) {
        userService.delete(user);
        addMessage(redirectAttributes, "删除用户成功");
        return "redirect:" + this.adminPath + "/user/?repage";
    }

    @RequestMapping(value = "disable")
    public String disable(User user, RedirectAttributes redirectAttributes) {
        userService.disable(user);
        addMessage(redirectAttributes, "禁用用户成功");
        return "redirect:" + this.adminPath + "/user/?repage";
    }

    @RequestMapping(value = "enable")
    public String enable(User user, RedirectAttributes redirectAttributes) {
        userService.enable(user);
        addMessage(redirectAttributes, "启用用户成功");
        return "redirect:" + this.adminPath + "/user/?repage";
    }
}
