package com.us.image.controller;

import com.us.common.utils.IdGen;
import com.us.image.entities.Account;
import com.us.image.exception.AccountAlreadyExistsException;
import com.us.image.service.AccountService;
import com.us.image.util.EncryptUtil;
import com.us.spring.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 18:15
 * @description :
 */
@Controller
@RequestMapping(value = "${frontPath}/access")
public class AccessController extends BaseController {

    // TODO 未采用 Shiro 的 Realm，因此不能使用 Shiro 的注解

    @Autowired
    private AccountService accountService;

    //@RequiresGuest
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model, @ModelAttribute(value = "errorMsg") String errorMessage) {
        model.addAttribute("errorMsg", errorMessage);
        return "modules/front/register";
    }

    //@RequiresGuest
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           RedirectAttributes redirectAttributes) {
        try {
            Account account = new Account(email, EncryptUtil.encrypt(password, email));
            account.setId(IdGen.uuid());
            account.setCreateDate(LocalDateTime.now());
            accountService.insert(account);
        } catch (AccountAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMsg", "该邮箱已经注册过了！");
            return "redirect:" + this.frontPath + "/access/register";
        }
        redirectAttributes.addFlashAttribute("registerMsg", "注册成功！");
        return "redirect:" + this.frontPath + "/access/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @ModelAttribute(value = "errorMsg") String errorMsg,
                        @ModelAttribute(value = "registerMsg") String registerMsg) {
        model.addAttribute("errorMsg", errorMsg);
        model.addAttribute("registerMsg", registerMsg);
        return "modules/front/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        Account account = accountService.selectByEmail(email);
        password = EncryptUtil.encrypt(password, email);
        if (account == null || !account.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("errorMsg", "用户名/密码错误");
            return "redirect:" + this.frontPath + "/access/login";
        }
        session.setAttribute("loginAccount", account);
        return "redirect:" + this.frontPath + "/access/index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "modules/front/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("loginAccount");
        session.invalidate();
        return "redirect:" + this.frontPath + "/access/login";
    }

}
