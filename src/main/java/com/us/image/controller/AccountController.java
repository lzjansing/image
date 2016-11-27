package com.us.image.controller;

import com.us.common.config.Global;
import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.utils.Message;
import com.us.common.utils.StringUtil;
import com.us.image.entities.*;
import com.us.image.service.*;
import com.us.spring.mvc.controller.BaseController;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 20:59
 * @description :
 */
@Controller
@RequestMapping(value = "${frontPath}/account")
public class AccountController extends BaseController {
    @Autowired
    private AccessController accessController;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RuleService ruleService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PraiseService praiseService;
    @Autowired
    private FocusService focusService;

    //已使用
    // TODO 未使用 Shiro，无法使用注解

    @ModelAttribute
    public void populateAccountIntoModel(Model model) {
        model.addAttribute("loginAccount", UserUtil.getAccount());
    }

    @RequiresAuthentication
    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String personCenter(@ModelAttribute(value = "loginAccount") Account loginAccount) {
        if (loginAccount == null) {
            return "redirect:" + this.frontPath + "/access/login";
        }
        return "modules/front/center";
    }

    /**
     * 发布分享
     *
     * @return
     */
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public String share(Share share, Model model) {
        boolean success = true;
        if (UserUtil.getAccount() == null) {
            model.addAttribute("errorMsg", "请先登录!");
        }
        if (share != null && StringUtil.isBlank(share.getImage())) {
            success = false;
            model.addAttribute("errorMsg", "请选择要分享的图片");
        }
        boolean filter = false;
        if (StringUtil.isNotBlank(share.getContent())) {
            Matcher matcher = Pattern.compile("foo").matcher(share.getContent());
            List<Rule> ruleList = ruleService.findList(new Rule());
            for (Rule rule : ruleList) {
                if (Rule.RULETYPE_NORMAL.equals(rule.getType())) {
                    filter = share.getContent().indexOf(rule.getKeyword()) >= 0;
                } else if (Rule.RULETYPE_REGEXP.equals(rule.getType())) {
                    matcher.reset();
                    filter = matcher.usePattern(Pattern.compile(rule.getKeyword())).find();
                }
                if (filter) {
                    break;
                }
            }
            if (filter) {
                success = false;
                model.addAttribute("errorMsg", "发布失败，包含敏感词");
            }
        }
        if (success) {
            share.setCollect(0);
            share.setComment(0);
            share.setPraise(0);
            share.setUser(new User(UserUtil.getAccount().getId()));
            shareService.save(share);
            return "redirect:/" + this.frontPath + "access/";
        } else {
            model.addAttribute("share", share);
            return accessController.index(model, null, null, null);
        }
    }

    @RequestMapping(value = "/deleteShare/{shareId}")
    public String deleteShare(@PathVariable String shareId) {
        shareService.deleteSelf(shareId);
        return "redirect:/" + this.frontPath + "access/";
    }

    @ResponseBody
    @RequestMapping(value = "/setSharePrivate/{shareId}", method = RequestMethod.GET)
    public Message setSharePrivate(@PathVariable String shareId) {
        Share share = new Share(shareId);
        share.setPrivated(Integer.valueOf(DictUtil.getDictValue("是", "yes_no", "")));
        Message message = shareService.updateSelf(share);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/unsetSharePrivate/{shareId}", method = RequestMethod.GET)
    public Message unsetSharePrivate(@PathVariable String shareId) {
        Share share = new Share(shareId);
        share.setPrivated(Integer.valueOf(DictUtil.getDictValue("否", "yes_no", "")));
        Message message = shareService.updateSelf(share);
        return message;
    }

    /**
     * urlExample:/setSharePermission/{shareId}/101
     *
     * @param shareId
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setSharePermission/{shareId}/{permission}", method = RequestMethod.GET)
    public Message setSharePermission(@PathVariable String shareId, @PathVariable String permission) {
        Share share = new Share(shareId);
        share.setPermission(permission.charAt(0) == '1', permission.charAt(1) == '1', permission.charAt(2) == '1');
        Message message = shareService.updateSelf(share);
        return message;
    }

    //收藏取收
    @ResponseBody
    @RequestMapping(value = "/collected/{shareId}", method = RequestMethod.GET)
    public Message collected(@PathVariable String shareId) {
        return collectService.collected(shareId);
    }

    @ResponseBody
    @RequestMapping(value = "/uncollected/{shareId}", method = RequestMethod.GET)
    public Message uncollected(@PathVariable String shareId) {
        return collectService.uncollected(shareId);
    }

    //点赞取赞
    @ResponseBody
    @RequestMapping(value = "/praised/{shareId}", method = RequestMethod.GET)
    public Message praised(@PathVariable String shareId) {
        return praiseService.praised(shareId);
    }

    @ResponseBody
    @RequestMapping(value = "/unpraised/{shareId}", method = RequestMethod.GET)
    public Message unpraised(@PathVariable String shareId) {
        return praiseService.unpraised(shareId);
    }

    //关注取关
    @ResponseBody
    @RequestMapping(value = "/focus/{userId}", method = RequestMethod.GET)
    public Message focus(@PathVariable String userId) {
        return focusService.focused(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/unfocus/{userId}", method = RequestMethod.GET)
    public Message unfocus(@PathVariable String userId) {
        return focusService.unfocused(userId);
    }

    //评论
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Message comment(Comment comment) {
        return commentService.comment(comment);
    }

    @ResponseBody
    @RequestMapping(value = "/uncomment/{commentId}", method = RequestMethod.GET)
    public Message uncomment(@PathVariable String commentId) {
        return commentService.uncomment(commentId);
    }

    @ResponseBody
    @RequestMapping(value = "/getComments/{shareId}", method = RequestMethod.GET)
    public Page<Comment> getComments(@PathVariable String shareId, Integer pageNo, Integer pageSize) {
        Comment comment = new Comment();
        comment.setShare(new Share(shareId));
        Page<Comment> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return commentService.findPage(page, comment);
    }

    @ResponseBody
    @RequestMapping(value = "/getCommentParents/{commentId}", method = RequestMethod.GET)
    public List<Comment> getCommentParents(@PathVariable String commentId) {
        return commentService.findParentList(commentService.get(commentId).getPids());
    }

    /**
     * todo 个人中心
     *
     * @param model
     * @param pageNo
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = {"/personal"}, method = RequestMethod.GET)
    public String personal(Model model, @RequestParam(required = false) Integer pageNo,
                           HttpServletRequest req) {
        if(UserUtil.getUser()!=null){
            //管理员不能看前台页面
            throw new AuthenticationException();
        }
        Page page = new Page();
        page.setPageNo(pageNo != null ? pageNo : 1);
        page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
        User currentUser = UserUtil.getAccount().getUser();
        User pageUser = currentUser;
        Share tmpShare = new Share();
        tmpShare.setUser(pageUser);
        tmpShare.setCurrentUser(currentUser);

        String by = req.getParameter("by");
        if (StringUtil.isNotBlank(by) && by.equals("focus")) {
            Account account = new Account();
            account.setId(currentUser.getId());
            page = accountService.findFocusPage(page, account);
            model.addAttribute("isShare", false);
            accountService.beFocused(page.getList(), currentUser);
        } else if (StringUtil.isNotBlank(req.getParameter("beFocused"))) {
            Account account = new Account();
            account.setId(currentUser.getId());
            page = accountService.findBeFocusedPage(page, account);
            model.addAttribute("isShare", false);
            accountService.beFocused(page.getList(), currentUser);
        } else {
            if (StringUtil.isNotBlank(by)) {
                if (by.equals("comment")) {
                    tmpShare.setComment(1);
                } else if (by.equals("collect")) {
                    tmpShare.setCollect(1);
                } else if (by.equals("praise")) {
                    tmpShare.setPraise(1);
                }
            }
            model.addAttribute("isShare", true);
            page = shareService.findPage(page, tmpShare);
        }
        model.addAttribute("by", by);

        model.addAttribute("page", page);

        //shareCount collectCount commentCount praiseCount
        tmpShare.setPraise(null);
        tmpShare.setComment(null);
        tmpShare.setCollect(null);
        int shareCount = shareService.count(tmpShare);
        model.addAttribute("shareCount", shareCount);
        tmpShare.setCollect(1);
        int collectCount = shareService.count(tmpShare);
        model.addAttribute("collectCount", collectCount);
        tmpShare.setCollect(null);
        tmpShare.setComment(1);
        int commentCount = shareService.count(tmpShare);
        model.addAttribute("commentCount", commentCount);
        tmpShare.setComment(null);
        tmpShare.setPraise(1);
        int praiseCount = shareService.count(tmpShare);
        model.addAttribute("praiseCount", praiseCount);

        Focus focus = new Focus();
        focus.setFromUser(pageUser);
        int focusCount = focusService.count(focus);
        focus.setFromUser(null);
        focus.setToUser(pageUser);
        int beFocusedCount = focusService.count(focus);
        model.addAttribute("focusCount", focusCount);
        model.addAttribute("beFocusedCount", beFocusedCount);

        model.addAttribute("pageUser", pageUser);
        model.addAttribute("loginUser", currentUser);
        model.addAttribute("action", "/account/personal");
        return "modules/front/personalManager";
    }
}
