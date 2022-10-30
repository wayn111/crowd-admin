package com.wayn.framework.shiro.credentials;

import com.wayn.common.util.DateUtils;
import com.wayn.common.util.SpringContextUtil;
import lombok.Data;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义密码验证服务
 */
@Data
public class MyCredentialsMatcher extends HashedCredentialsMatcher {

    private Integer retryCount;

    /**
     * 检查密码重试次数，防止暴力破解
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CacheManager cacheManager = SpringContextUtil.getBean(CacheManager.class);
        Cache passwordRetryCache = cacheManager.getCache("passwordRetryCache");

        String userName = (String) token.getPrincipal();
        //retry count + 1
        Element element = passwordRetryCache.get(userName);
        if (element == null) {
            element = new Element(userName, new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger count = (AtomicInteger) element.getObjectValue();
        if (count.incrementAndGet() > retryCount) {
            // 计算过期至今的时间
            String timeBefore = DateUtils.getTimeAfter(new Date(element.getExpirationTime()));
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException("该账号密码重试次数过多，请在" + timeBefore + "重试");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry count
            passwordRetryCache.remove(userName);
        }
        return matches;
    }
}
