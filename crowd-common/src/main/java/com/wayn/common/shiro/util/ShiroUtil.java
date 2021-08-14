package com.wayn.common.shiro.util;

import com.wayn.common.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String md5encrypt(String password, Object salt) {
        return new SimpleHash("MD5", password, salt, 1024).toString();
    }

    /**
     * 获取当前Session中的用户
     *
     * @return
     */
    public static User getSessionUser() {

        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Object object = subject.getPrincipal();
            if (object != null) {
                User sysUser = (User) object;
                return sysUser;
            }
        }
        return null;
    }

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static String getSessionUid() {

        User sysUser = getSessionUser();

        if (sysUser != null) {

            return sysUser.getId();
        }

        return null;
    }

    /**
     * 获取请求ip
     *
     * @return
     */
    public static String getIP() {
        return SecurityUtils.getSubject().getSession().getHost();
    }
}
