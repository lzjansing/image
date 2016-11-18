package com.us.common.modules.sys.security;

import org.apache.shiro.session.Session;

import java.util.Collection;

/**
 * Created by jansing on 16-11-16.
 */
public interface SessionDAO extends org.apache.shiro.session.mgt.eis.SessionDAO {
    Collection<Session> getActiveSessions(boolean var1);

    Collection<Session> getActiveSessions(boolean var1, Object var2, Session var3);

}
