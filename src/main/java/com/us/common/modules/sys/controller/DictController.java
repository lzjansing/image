package com.us.common.modules.sys.controller;

import com.us.common.modules.sys.entities.Dict;
import com.us.common.modules.sys.service.DictService;
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
import java.util.List;

/**
 * Created by jansing on 16-11-13.
 */
@Controller
@RequestMapping({"${adminPath}/sys/dict"})
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;

    @ModelAttribute
    public Dict get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? dictService.get(id) : new Dict();
    }

    @RequestMapping({"list", ""})
    public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("typeList", dictService.findTypeList());
        Page page = dictService.findPage(new Page(request, response), dict);
        model.addAttribute("page", page);
        return "modules/sys/dictList";
    }

    @RequestMapping({"form"})
    public String form(Dict dict, Model model) {
        model.addAttribute("dict", dict);
        return "modules/sys/dictForm";
    }

    @RequestMapping({"save"})
    public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
        if (!this.beanValidator(model, dict)) {
            return this.form(dict, model);
        } else {
            dictService.save(dict);
            addMessage(redirectAttributes, "保存字典成功");
            return "redirect:" + this.adminPath + "/sys/dict/?repage&type=" + dict.getType();
        }
    }

    @RequestMapping({"delete"})
    public String delete(Dict dict, RedirectAttributes redirectAttributes) {
        this.dictService.delete(dict);
        this.addMessage(redirectAttributes, "删除字典成功");
        return "redirect:" + this.adminPath + "/sys/dict/?repage&type=" + dict.getType();
    }

    @ResponseBody
    @RequestMapping({"listData"})
    public List<Dict> listData(@RequestParam(required = false) String type) {
        Dict dict = new Dict();
        dict.setType(type);
        return this.dictService.findList(dict);
    }
}
