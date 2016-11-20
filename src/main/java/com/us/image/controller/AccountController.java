package com.us.image.controller;

import com.us.image.entities.Account;
import com.us.spring.mvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 20:59
 * @description :
 */
@Controller
@RequestMapping(value = "${frontPath}/account")
public class AccountController extends BaseController {

    // TODO 未使用 Shiro，无法使用注解

    @ModelAttribute
    public void populateAccountIntoModel(Model model, HttpSession session) {
        model.addAttribute("loginAccount", session.getAttribute("loginAccount"));
    }

    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String personCenter(@ModelAttribute(value = "loginAccount") Account loginAccount) {
        if (loginAccount == null) {
            return "redirect:" + this.frontPath + "/access/login";
        }
        return "modules/front/center";
    }

}
