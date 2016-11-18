package com.us.image.entities;

import com.us.common.modules.sys.entities.User;
import com.us.common.persistence.DataEntity;

/**
 * Created by jansing on 16-11-8.
 */
public class Focus extends DataEntity<Focus> {
    private User fromUser;
    private User toUser;

    public Focus() {
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

    @Override
    public String toString() {
        return "Focus{" +
                "fromUser=" + fromUser.toString() +
                ", toUser=" + toUser.toString() +
                '}';
    }
}
