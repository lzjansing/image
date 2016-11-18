package com.us.image.entities;

import com.us.common.modules.sys.entities.User;
import com.us.common.persistence.DataEntity;

/**
 * Created by jansing on 16-11-8.
 */
public class Praise extends DataEntity<Praise> {
    private User fromUser;
    private User toUser;
    private Share share;

    public Praise() {
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

    @Override
    public String toString() {
        return "Praise{" +
                "fromUser=" + fromUser.toString() +
                ", toUser=" + toUser.toString() +
                ", share=" + share.toString() +
                '}';
    }
}
