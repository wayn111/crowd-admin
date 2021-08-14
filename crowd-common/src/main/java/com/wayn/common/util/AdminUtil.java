package com.wayn.common.util;

/**
 * 管理员帮助类
 */
public class AdminUtil {

    public static boolean isAdmin(String userId) {
        String adminId = ProperUtil.get("wayn.adminId");
        if (adminId.equals(userId)) {
            return true;
        }
        return false;
    }
}
