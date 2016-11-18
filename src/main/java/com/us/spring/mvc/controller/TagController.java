package com.us.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jansing on 16-11-12.
 */
@Controller
@RequestMapping({"${adminPath}/tag"})
public class TagController extends BaseController {

    @RequestMapping({"iconselect/{id}"})
    public String iconselect(@PathVariable String id, HttpServletRequest request, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("value", request.getParameter("value"));
        return "include/tagIconSelect";
    }
}