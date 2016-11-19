package com.us.image.controller;

import com.google.common.collect.Maps;
import com.us.common.modules.sys.security.SessionDAO;
import com.us.common.modules.sys.security.SystemAuthorizingRealm;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.utils.IdGen;
import com.us.common.utils.StringUtil;
import com.us.spring.mvc.controller.BaseController;
import com.us.spring.utils.CacheUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jansing on 16-11-19.
 */
@Controller
public class FrontLoginController extends BaseController {
    @Autowired
    private SessionDAO sessionDAO;

    @RequestMapping(value="${frontPath}/login", method = RequestMethod.GET)
    public String login(){
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("login, active session size: {}", Integer.valueOf(this.sessionDAO.getActiveSessions(false).size()));
        }

        return principal != null ? "redirect:/" + this.frontPath : "login";
    }

    @RequestMapping(value = {"${frontPath}/login"}, method = {RequestMethod.POST})
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
        if (principal != null) {
            return "redirect:" + this.adminPath;
        } else {
            String username = WebUtils.getCleanParam(request, "username");
            Boolean mobileLogin = Boolean.valueOf(WebUtils.isTrue(request, "mobileLogin"));
            String exception = (String) request.getAttribute("shiroLoginFailure");
            String message = (String) request.getAttribute("message");
            if (StringUtil.isBlank(message) || StringUtil.equals(message, "null")) {
                message = "用户或密码错误, 请重试.";
            }

            model.addAttribute("username", username);
            model.addAttribute("mobileLogin", mobileLogin);
            model.addAttribute("shiroLoginFailure", exception);
            model.addAttribute("message", message);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("login fail, active session size: {}, username: {}, message: {}, exception: {}", new Object[]{Integer.valueOf(this.sessionDAO.getActiveSessions(false).size()), username, message, exception});
            }

            if (!UnauthorizedException.class.getName().equals(exception)) {
                model.addAttribute("isValidateCodeLogin", Boolean.valueOf(isValidateCodeLogin(username, true, false)));
            }

            request.getSession().setAttribute("validateCode", IdGen.uuid());
            return "login";
        }
    }

    @RequestMapping({"${frontPath}"})
    public String index() {
        SystemAuthorizingRealm.Principal principal = UserUtil.getPrincipal();
        if(principal!=null) {
            isValidateCodeLogin(principal.getUsername(), false, true);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("show index, active session size: {}", Integer.valueOf(this.sessionDAO.getActiveSessions(false).size()));
            }
        }

        return "index";
    }

    //判断登录失败的次数是否大于等于三次
    public static boolean isValidateCodeLogin(String username, boolean isFail, boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtil.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtil.put("loginFailMap", loginFailMap);
        }

        Integer loginFailNum = loginFailMap.get(username);
        if (loginFailNum == null) {
            loginFailNum = Integer.valueOf(0);
        }

        if (isFail) {
            loginFailNum = Integer.valueOf(loginFailNum.intValue() + 1);
            loginFailMap.put(username, loginFailNum);
        }

        if (clean) {
            loginFailMap.remove(username);
        }

        return loginFailNum.intValue() >= 3;
    }



}
