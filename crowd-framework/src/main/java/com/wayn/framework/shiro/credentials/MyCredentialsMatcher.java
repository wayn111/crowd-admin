package com.wayn.framework.shiro.credentials;

import com.wayn.common.service.CacheManagerService;
import com.wayn.common.util.DateUtils;
import com.wayn.common.util.SpringContextUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义密码验证服务
 */
@EqualsAndHashCode(callSuper = true)
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
        CacheManagerService<AtomicInteger> cacheManagerService = SpringContextUtil.getBean(CacheManagerService.class);
        Cache passwordRetryCache = cacheManagerService.getCache("passwordRetryCache");
        String userName = (String) token.getPrincipal();
        // retry count + 1
        Long num = passwordRetryCache.get(userName, Long.class);
        if (num == null) {
            num = 0L;
        }
        passwordRetryCache.put(userName, ++num);
        if (num > retryCount) {
            // 计算过期至今的时间
            Long expireTime = cacheManagerService.ttl("passwordRetryCache", userName);
            String timeBefore = DateUtils.getTimeAfter(new Date(expireTime));
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException("该账号密码重试次数过多，请在" + timeBefore + "重试");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // clear retry count
            passwordRetryCache.evict(userName);
        }
        return matches;
    }
}
