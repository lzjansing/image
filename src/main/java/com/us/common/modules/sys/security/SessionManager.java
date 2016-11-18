package com.us.common.modules.sys.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by jansing on 16-11-17.
 */
public class SessionManager extends DefaultWebSessionManager {
    public SessionManager() {
    }

    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sid = request.getParameter("__sid");
        if (StringUtils.isNotBlank(sid)) {
            if (WebUtils.isTrue(request, "__cookie")) {
                HttpServletRequest rq = (HttpServletRequest) request;
                HttpServletResponse rs = (HttpServletResponse) response;
                Cookie template = this.getSessionIdCookie();
                SimpleCookie cookie = new SimpleCookie(template);
                cookie.setValue(sid);
                cookie.saveTo(rq, rs);
            }

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "url");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sid;
        } else {
            return super.getSessionId(request, response);
        }
    }

    public void validateSessions() {
        super.validateSessions();
    }

    protected Session retrieveSession(SessionKey sessionKey) {
        try {
            return super.retrieveSession(sessionKey);
        } catch (UnknownSessionException var3) {
            return null;
        }
    }

    public Date getStartTimestamp(SessionKey key) {
        try {
            return super.getStartTimestamp(key);
        } catch (InvalidSessionException var3) {
            return null;
        }
    }

    public Date getLastAccessTime(SessionKey key) {
        try {
            return super.getLastAccessTime(key);
        } catch (InvalidSessionException var3) {
            return null;
        }
    }

    public long getTimeout(SessionKey key) {
        try {
            return super.getTimeout(key);
        } catch (InvalidSessionException var3) {
            return 0L;
        }
    }

    public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {
        try {
            super.setTimeout(key, maxIdleTimeInMillis);
        } catch (InvalidSessionException var5) {
        }

    }

    public void touch(SessionKey key) {
        try {
            super.touch(key);
        } catch (InvalidSessionException var3) {
        }

    }

    public String getHost(SessionKey key) {
        try {
            return super.getHost(key);
        } catch (InvalidSessionException var3) {
            return null;
        }
    }

    public Collection<Object> getAttributeKeys(SessionKey key) {
        try {
            return super.getAttributeKeys(key);
        } catch (InvalidSessionException var3) {
            return null;
        }
    }

    public Object getAttribute(SessionKey sessionKey, Object attributeKey) {
        try {
            return super.getAttribute(sessionKey, attributeKey);
        } catch (InvalidSessionException var4) {
            return null;
        }
    }

    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {
        try {
            super.setAttribute(sessionKey, attributeKey, value);
        } catch (InvalidSessionException var5) {
        }

    }

    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {
        try {
            return super.removeAttribute(sessionKey, attributeKey);
        } catch (InvalidSessionException var4) {
            return null;
        }
    }

    public void stop(SessionKey key) {
        try {
            super.stop(key);
        } catch (InvalidSessionException var3) {
        }

    }

    public void checkValid(SessionKey key) {
        try {
            super.checkValid(key);
        } catch (InvalidSessionException var3) {
        }

    }

    protected Session doCreateSession(SessionContext context) {
        try {
            return super.doCreateSession(context);
        } catch (IllegalStateException var3) {
            return null;
        }
    }

    protected Session newSessionInstance(SessionContext context) {
        Session session = super.newSessionInstance(context);
        session.setTimeout(this.getGlobalSessionTimeout());
        return session;
    }

    public Session start(SessionContext context) {
        try {
            return super.start(context);
        } catch (NullPointerException var4) {
            SimpleSession session = new SimpleSession();
            session.setId(Integer.valueOf(0));
            return session;
        }
    }
}
