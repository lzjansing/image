package com.us.image.entities;

import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * Created by jansing on 16-11-8.
 */
public class Share extends DataEntity<Share> {
    private User user;
    private String content;
    private String image;
    private Integer privated;
    private Integer permission;
    private Integer praise;
    private Integer collect;
    private Integer comment;
    public static final int CAN_PRAISE = 001;
    public static final int CAN_COLLECT = 010;
    public static final int CAN_COMMENT = 100;
    //是否被当前用用户操作过
    private User currentUser;
    private Integer hadCollected;
    private Integer hadPraised;

    public Share() {
    }

    public Share(String id) {
        super(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Length(min = 1, max = 350, message = "请输入1～300个字符的内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 图片地址，用Global.getConfig("string.split")拼接
     *
     * @return
     */
    @Length(max = 400, message = "系统错误，请重试")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 是否私有，即不公开
     * 私有   1
     * 公开   0
     *
     * @return
     */
    @Range(min = 0, max = 1, message = "系统错误，请重试")
    public Integer getPrivated() {
        return privated;
    }

    public void setPrivated(Integer privated) {
        this.privated = privated;
    }

    /**
     * 是否可xx，
     * 点赞	001
     * 收藏	010
     * 评论	100
     *
     * @return
     */
    @Range(min = 0, max = 7, message = "系统错误，请重试")
    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public boolean isCanPraise() {
        return permission != null && (permission & CAN_PRAISE) > 0 ? true : false;
    }

    public boolean isCanCollect() {
        return permission != null && (permission & CAN_COLLECT) > 0 ? true : false;
    }

    public boolean isCanComment() {
        return permission != null && (permission & CAN_COMMENT) > 0 ? true : false;
    }

    public void setPermission(boolean canPraise, boolean canCollect, boolean canComment) {
        setPermission(0 & (canPraise ? CAN_PRAISE : 0)
                & (canCollect ? CAN_COLLECT : 0)
                & (canComment ? CAN_COMMENT : 0));
    }

    /**
     * 点赞量
     *
     * @return
     */
    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * 收藏量
     *
     * @return
     */
    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    /**
     * 评论量
     *
     * @return
     */
    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Integer getHadCollected() {
        return hadCollected;
    }

    public void setHadCollected(Integer hadCollected) {
        this.hadCollected = hadCollected;
    }

    public Integer getHadPraised() {
        return hadPraised;
    }

    public void setHadPraised(Integer hadPraised) {
        this.hadPraised = hadPraised;
    }

    @Override
    public String toString() {
        return "Share{" +
                "user=" + user.toString() +
                ", contentLength='" + content.length() + '\'' +
                ", image='" + image + '\'' +
                ", privated=" + privated +
                ", permission=" + permission +
                ", praise=" + praise +
                ", collect=" + collect +
                ", comment=" + comment +
                '}';
    }
}
