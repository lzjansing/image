package com.us.image.entities;

import com.us.common.modules.sys.entities.User;
import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * Created by jansing on 16-11-8.
 */
public class Comment extends DataEntity<Comment> {
    private User fromUser;
    private User toUser;
    private Share share;
    private Comment parent;
    private String pids;
    private String content;

    public Comment() {
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    @Length(min = 1, max = 150, message = "请输入1～100个字符的评论")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "fromUser=" + fromUser.toString() +
                ", toUser=" + toUser.toString() +
                ", share=" + share.toString() +
                ", parent=" + parent +
                ", pids='" + pids + '\'' +
                ", contentLength='" + content.length() + '\'' +
                '}';
    }
}
