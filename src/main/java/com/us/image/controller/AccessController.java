package com.us.image.controller;

import com.us.common.config.Global;
import com.us.common.modules.sys.security.SystemAuthorizingRealm;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.utils.IdGen;
import com.us.common.utils.StringUtil;
import com.us.image.entities.Account;
import com.us.image.entities.Focus;
import com.us.image.entities.Share;
import com.us.image.entities.User;
import com.us.image.exception.AccountAlreadyExistsException;
import com.us.image.service.AccountService;
import com.us.image.service.FocusService;
import com.us.image.service.ShareService;
import com.us.image.util.EncryptUtil;
import com.us.spring.mvc.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    //已采用
    // TODO 未采用 Shiro 的 Realm，因此不能使用 Shiro 的注解

    @Autowired
    private AccountService accountService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private FocusService focusService;

    @RequiresGuest
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model, @ModelAttribute(value = "errorMsg") String errorMessage) {
        model.addAttribute("errorMsg", errorMessage);
        return "modules/front/register";
    }

    @RequiresGuest
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
        return UserUtil.getPrincipal() != null ? "redirect:/" + this.frontPath + "access/" : "modules/front/login";
    }

    /**
     * 被shiro拦截，如果登录成功跳到主页，如果失败才调用这个方法。
     *
     * @param request
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
        if (principal != null) {
            return "redirect:" + this.frontPath + "/access/index";
        } else {
            String errorMsg = (String) request.getAttribute("message");
            if (StringUtil.isBlank(errorMsg) || StringUtil.equals(errorMsg, "null")) {
                errorMsg = "用户名/密码错误";
            }
            model.addAttribute("errorMsg", errorMsg);
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
            return "redirect:" + this.frontPath + "/access/login";
        }
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, @RequestParam(required = false) String searchInput,
                        @RequestParam(required = false) String byPraise,
                        @RequestParam(required = false) Integer pageNo) {
        if (!model.containsAttribute("share")) {
            Share share = new Share();
            share.setPrivated(Share.NO);
            model.addAttribute("share", share);
        }
        Page<Share> page = new Page<>();
        page.setPageNo(pageNo != null ? pageNo : 1);
        page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
        Share tmpShare = new Share();
        if (StringUtil.isNotBlank(searchInput)) {
            tmpShare.setContent(searchInput);
            model.addAttribute("searchInput", searchInput);
        } else if (StringUtil.isNotBlank(byPraise)) {
            page.setOrderBy("a.praise desc");
            model.addAttribute("byPraise", byPraise);
        }
        page = shareService.findPage(page, tmpShare);
        model.addAttribute("page", page);

        User currentUser = UserUtil.getAccount().getUser();
        if (currentUser != null) {
            Share tmpShare2 = new Share();
            tmpShare2.setUser(currentUser);
            tmpShare2.setCurrentUser(currentUser);
            int shareCount = shareService.findList(tmpShare2).size();
            Focus focus = new Focus();
            focus.setFromUser(currentUser);
            int focusCount = focusService.count(focus);
            focus.setFromUser(null);
            int beFocusedCount = focusService.count(focus);
            model.addAttribute("shareCount", shareCount);
            model.addAttribute("focusCount", focusCount);
            model.addAttribute("beFocusedCount", beFocusedCount);
        }
        return "modules/front/lindex";
    }

    /**
     * todo 他人的个人中心
     */
    @RequestMapping(value = {"/otherPersonal/{userId}"}, method = RequestMethod.GET)
    public String otherPersonal(Model model, @PathVariable String userId,
                                @RequestParam(required = false) Integer pageNo) {
        Share tmpShare = new Share();
        tmpShare.setUser(new User(userId));
        tmpShare.setCurrentUser(UserUtil.getAccount().getUser());
        Page<Share> page = new Page<>();
        page.setPageNo(pageNo != null ? pageNo : 1);
        page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
        page = shareService.findPage(page, tmpShare);
        model.addAttribute("page", page);
        //todo focusCount beFoucsedCount shareCount
        return "modules/front/lindex";
    }

}
