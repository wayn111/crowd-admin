package com.wayn.framework.util;

import com.wayn.framework.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

/**
 * Shiro工具类
 *
 * @author jameszhou
 */
public class ShiroCacheUtil {

    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm shiroRealm = (MyRealm) rsm.getRealms().iterator().next();
        shiroRealm.clearCachedAuthorizationInfo();

		/*Subject subject = SecurityUtils.getSubject();
		String realmName = subject.getPrincipals().getRealmNames().iterator().next();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(subject.getPrincipal(), realmName);
		subject.runAs(principals);
		//用realm删除principle
		shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
		//切换身份也就是刷新了
		subject.releaseRunAs();*/

    }
}
