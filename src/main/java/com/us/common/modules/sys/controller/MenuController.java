package com.us.common.modules.sys.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.us.common.config.Global;
import com.us.common.modules.sys.entities.Menu;
import com.us.common.modules.sys.service.SystemService;
import com.us.common.utils.StringUtil;
import com.us.spring.mvc.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by jansing on 16-11-18.
 */
@Controller
@RequestMapping({"${adminPath}/sys/menu"})
public class MenuController extends BaseController {
    @Autowired
    private SystemService systemService;

    public MenuController() {
    }

    @ModelAttribute("menu")
    public Menu get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? this.systemService.getMenu(id) : new Menu();
    }

    /**
     * 取到模块
     *
     * @param model
     * @return
     */
    @RequiresPermissions({"sys:menu:view"})
    @RequestMapping({"list", ""})
    public String list(Model model) {
        ArrayList list = Lists.newArrayList();
        List sourcelist = this.systemService.findAllMenu();
        Menu.sortList(list, sourcelist, Menu.getRootId(), true);
        model.addAttribute("list", list);
        return "modules/sys/menuList";
    }

    @RequiresPermissions({"sys:menu:view"})
    @RequestMapping({"form"})
    public String form(Menu menu, Model model) {
        if (menu.getParent() == null || menu.getParent().getId() == null) {
            menu.setParent(new Menu(Global.getRootMenuId()));
        }

        menu.setParent(this.systemService.getMenu(menu.getParent().getId()));
        if (StringUtil.isBlank(menu.getId())) {
            ArrayList<Menu> list = Lists.newArrayList();
            List<Menu> sourcelist = this.systemService.findAllMenu();
            Menu.sortList(list, sourcelist, menu.getParent().getId(), false);
            if (list.size() > 0) {
                menu.setSort(Integer.valueOf((list.get(list.size() - 1)).getSort().intValue() + 30));
            }
        }

        model.addAttribute("menu", menu);
        return "modules/sys/menuForm";
    }

    @RequiresPermissions({"sys:menu:edit"})
    @RequestMapping({"save"})
    public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
        this.systemService.saveMenu(menu);
        this.addMessage(redirectAttributes, "保存菜单\'" + menu.getName() + "\'成功");
        return "redirect:" + this.adminPath + "/sys/menu/";
    }

    @RequiresPermissions({"sys:menu:edit"})
    @RequestMapping({"delete"})
    public String delete(Menu menu, RedirectAttributes redirectAttributes) {
        this.systemService.deleteMenu(menu);
        this.addMessage(redirectAttributes, "删除菜单成功");
        return "redirect:" + this.adminPath + "/sys/menu/";
    }

    @RequiresPermissions({"sys:menu:edit"})
    @RequestMapping({"updateSort"})
    public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
        for (int i = 0; i < ids.length; ++i) {
            Menu menu = new Menu(ids[i]);
            menu.setSort(sorts[i]);
            this.systemService.updateMenuSort(menu);
        }

        this.addMessage(redirectAttributes, "保存菜单排序成功!");
        return "redirect:" + this.adminPath + "/sys/menu/";
    }

    /**
     * 找出parentId下的子节点，如果parentId=null，则找root下的子节点
     * todo extId和isShowHide是什么鬼
     *
     * @param parentId
     * @param extId
     * @param isShowHide
     * @param response
     * @return
     */
    @RequiresAuthentication
    @ResponseBody
    @RequestMapping({"treeData"})
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String parentId,
                                              @RequestParam(required = false) String extId, @RequestParam(required = false) String isShowHide,
                                              HttpServletResponse response) {
        ArrayList<Map<String, Object>> mapList = Lists.newArrayList();
        List<Menu> list = this.systemService.findAllMenu();

        for (int i = 0; i < list.size(); ++i) {
            Menu e = list.get(i);
            //extId不是当前节点id，也不是祖父节点
            if ((StringUtil.isBlank(extId) || extId != null && !extId.equals(e.getId()) && e.getPids().indexOf("," + extId + ",") == -1)
                    && (isShowHide == null || !isShowHide.equals("0") || !e.getIsShow().equals("0"))) {
                HashMap<String, Object> map;
                if (parentId == null) {
                    if (e.getParent().getId().equals("0")) {
                        map = Maps.newHashMap();
                        map.put("id", e.getId());
                        map.put("pId", e.getParent().getId());
                        map.put("name", e.getName());
                        mapList.add(map);
                    }
                } else {
                    if (parentId.equals(e.getParent().getId())) {
                        map = Maps.newHashMap();
                        map.put("id", e.getId());
                        map.put("pId", parentId);
                        map.put("name", e.getName());
                        map.put("isItem", Boolean.valueOf(true));
                        mapList.add(map);

                        Iterator<Menu> var11 = list.iterator();

                        while (var11.hasNext()) {
                            Menu menu = var11.next();
                            if (menu.getPids().indexOf(map.get("id").toString()) != -1) {
                                map.put("isItem", Boolean.valueOf(false));
                                break;
                            }
                        }
                    }
                }
            }
        }

        return mapList;
    }
}