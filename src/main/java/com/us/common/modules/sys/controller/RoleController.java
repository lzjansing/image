package com.us.common.modules.sys.controller;

import com.us.common.modules.sys.entities.Role;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.service.SystemService;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.utils.Collections3;
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

import java.util.List;

/**
 * Created by jansing on 16-11-18.
 */
@Controller
@RequestMapping({"${adminPath}/sys/role"})
public class RoleController extends BaseController {
    @Autowired
    private SystemService systemService;

    @ModelAttribute("role")
    public Role get(@RequestParam(required = false) String id) {
        return StringUtil.isNotBlank(id) ? this.systemService.getRole(id) : new Role();
    }

    @RequiresPermissions({"sys:role:view"})
    @RequestMapping({"list", ""})
    public String list(Role role, Model model) {
        List list = this.systemService.findAllRole();
        model.addAttribute("list", list);
        return "modules/sys/roleList";
    }

    @RequiresPermissions({"sys:role:view"})
    @RequestMapping({"form"})
    public String form(Role role, Model model) {
        model.addAttribute("role", role);
        model.addAttribute("menuList", this.systemService.findAllMenu());
        return "modules/sys/roleForm";
    }

    @RequiresPermissions({"sys:role:edit"})
    @RequestMapping({"save"})
    public String save(Role role, Model model, RedirectAttributes redirectAttributes) {
        if (!this.beanValidator(model, role, new Class[0])) {
            return this.form(role, model);
        }
        if (!"true".equals(this.checkName(role))) {
            this.addMessage(model, "保存角色\'" + role.getName() + "\'失败, 角色名已存在");
            return this.form(role, model);
        }
        if (!"true".equals(this.checkEnname(role))) {
            this.addMessage(model, "保存角色\'" + role.getName() + "\'失败, 英文名已存在");
            return this.form(role, model);
        }
        this.systemService.saveRole(role);
        this.addMessage(redirectAttributes, "保存角色\'" + role.getName() + "\'成功");
        return "redirect:" + this.adminPath + "/sys/role/?repage";
    }

    @RequiresPermissions({"sys:role:edit"})
    @RequestMapping({"delete"})
    public String delete(Role role, RedirectAttributes redirectAttributes) {
        this.systemService.deleteRole(role);
        this.addMessage(redirectAttributes, "删除角色成功");
        return "redirect:" + this.adminPath + "/sys/role/?repage";
    }

    @RequiresPermissions({"sys:role:edit"})
    @RequestMapping({"assign"})
    public String assign(Role role, Model model) {
        List userList = this.systemService.findUser(new User(new Role(role.getId())));
        model.addAttribute("userList", userList);
        return "modules/sys/roleAssign";
    }

    @RequiresPermissions({"sys:role:view"})
    @RequestMapping({"usertorole"})
    public String selectUserToRole(Role role, Model model) {
        User tmpUser = new User();
        tmpUser.setValid(null);
        model.addAttribute("userList", this.systemService.findUser(tmpUser));
        model.addAttribute("role", role);
        List selectedList = this.systemService.findUser(new User(role));
        model.addAttribute("selectedList", selectedList);
        //todo id 原来是 name
        model.addAttribute("selectIds", Collections3.extractToString(selectedList, "id", ","));
        return "modules/sys/selectUserToRole";
    }

    @RequiresPermissions({"sys:role:edit"})
    @RequestMapping({"outrole"})
    public String outrole(String userId, String roleId, RedirectAttributes redirectAttributes) {
        Role role = this.systemService.getRole(roleId);
        User user = this.systemService.getUser(userId);
        if (UserUtil.getUser().getId().equals(userId)) {
            this.addMessage(redirectAttributes, "无法从角色【" + role.getName() + "】中移除用户【" + user.getUsername() + "】自己！");
        } else if (user.getRoleList().size() <= 1) {
            this.addMessage(redirectAttributes, "用户【" + user.getUsername() + "】从角色【" + role.getName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
        } else {
            Boolean flag = this.systemService.outUserInRole(role, user);
            if (!flag.booleanValue()) {
                this.addMessage(redirectAttributes, "用户【" + user.getUsername() + "】从角色【" + role.getName() + "】中移除失败！");
            } else {
                this.addMessage(redirectAttributes, "用户【" + user.getUsername() + "】从角色【" + role.getName() + "】中移除成功！");
            }
        }

        return "redirect:" + this.adminPath + "/sys/role/assign?id=" + role.getId();
    }

    @RequiresPermissions({"sys:role:edit"})
    @RequestMapping({"assignrole"})
    public String assignRole(Role role, String[] userIds, RedirectAttributes redirectAttributes) {
        StringBuilder msg = new StringBuilder();
        int newNum = 0;

        for (int i = 0; i < userIds.length; ++i) {
            User user = this.systemService.assignUserToRole(role, this.systemService.getUser(userIds[i]));
            if (null != user) {
                msg.append("<br/>新增用户【" + user.getUsername() + "】到角色【" + role.getName() + "】！");
                ++newNum;
            }
        }

        this.addMessage(redirectAttributes, "已成功分配 " + newNum + " 个用户" + msg);
        return "redirect:" + this.adminPath + "/sys/role/assign?id=" + role.getId();
    }

    @RequiresAuthentication
    @ResponseBody
    @RequestMapping({"checkName"})
    public String checkName(Role role) {
        Role tmpRole = systemService.getRoleByName(role.getName());
        return (StringUtil.isBlank(role.getId()) && tmpRole == null) || role.getId().equals(tmpRole.getId()) ? "true" : "false";
    }

    @RequiresAuthentication
    @ResponseBody
    @RequestMapping({"checkEnname"})
    public String checkEnname(Role role) {
        Role tmpRole = systemService.getRoleByEnname(role.getEnname());
        return (StringUtil.isBlank(role.getId()) && tmpRole == null) || role.getId().equals(tmpRole.getId()) ? "true" : "false";
    }
}

