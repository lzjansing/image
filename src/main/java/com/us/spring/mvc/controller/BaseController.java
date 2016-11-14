package com.us.spring.mvc.controller;


import com.us.common.beanvalidator.BeanValidators;
import com.us.common.mapper.JsonMapper;
import com.us.spring.mvc.utils.MyBooleanEditor;
import com.us.spring.mvc.utils.MyDateEditor;
import com.us.spring.mvc.utils.MyIntegerEditor;
import com.us.spring.mvc.utils.MyStringEditor;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by jansing on 16-11-5.
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${adminPath}")
    protected String adminPath;
    @Value("${frontPath}")
    protected String frontPath;
    @Autowired
    protected Validator validator;

    protected void beanValidator(Object object, Class... groups) {
        BeanValidators.validateWithException(this.validator, object, groups);
    }

    protected boolean beanValidator(Model model, Object object, Class... groups) {
        try {
            beanValidator(object, groups);
            return true;
        } catch (ConstraintViolationException e) {
            List list = BeanValidators.extractPropertyAndMessageAsList(e, ": ");
            list.add(0, "数据验证失败：");
            this.addMessage(model, (String[]) list.toArray(new String[0]));
            return false;
        }
    }

    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class... groups) {
        try {
            BeanValidators.validateWithException(this.validator, object, groups);
            return true;
        } catch (ConstraintViolationException e) {
            List list = BeanValidators.extractPropertyAndMessageAsList(e, ": ");
            list.add(0, "数据验证失败：");
            this.addMessage(redirectAttributes, (String[]) list.toArray(new String[0]));
            return false;
        }
    }

    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.length; ++i) {
            String message = messages[i];
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }

        model.addAttribute("message", sb.toString());
    }

    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.length; ++i) {
            String message = messages[i];
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }

        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    protected String renderString(HttpServletResponse response, Object object) {
        return this.renderString(response, JsonMapper.toJsonString(object), "application/json");
    }

    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            response.flushBuffer();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {
        return "error/400";
    }

    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {
        return "error/403";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new MyStringEditor());
        binder.registerCustomEditor(Boolean.class, new MyBooleanEditor());
        binder.registerCustomEditor(Integer.class, new MyIntegerEditor());
        binder.registerCustomEditor(Date.class, new MyDateEditor());
    }
}
