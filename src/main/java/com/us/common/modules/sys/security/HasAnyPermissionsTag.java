package com.us.common.modules.sys.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * Created by jansing on 16-11-19.
 */
public class HasAnyPermissionsTag extends PermissionTag {
    private static final long serialVersionUID = 1L;
    private static final String PERMISSION_NAMES_DELIMETER = ",";

    public HasAnyPermissionsTag() {
    }

    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;
        Subject subject = this.getSubject();
        if (subject != null) {
            String[] permissions = permissionNames.split(",");

            for (int i = 0; i < permissions.length; ++i) {
                String permission = permissions[i];
                if (subject.isPermitted(permission.trim())) {
                    hasAnyPermission = true;
                    break;
                }
            }
        }

        return hasAnyPermission;
    }
}
