package com.wayn.framework.shiro.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.commom.constant.Constant;
import com.wayn.commom.domain.User;
import com.wayn.commom.enums.StateEnum;
import com.wayn.commom.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Collection;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private LogininforService logininforService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 密码加密 测试
     *
     * @param args 测试
     */
    public static void main(String[] args) {
        // MD5,"原密码","盐",加密次数
        String pwd = new SimpleHash("MD5", "123456", "admin", 1024).toString();
        System.out.println(pwd);
        System.out.println(Boolean.valueOf("true"));
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User sysUser = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = userRoleService.findRolesByUid(sysUser.getId());
        Set<String> permissions = roleMenuService.findMenusByUid(sysUser.getId());
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;
        String username = token2.getUsername();
        // String password = ShiroUtil.md5encrypt(new String(token2.getPassword()), username);
        User sysUser = userService.selectOne(new EntityWrapper<User>().eq("userName", username));
        if (sysUser == null) {
            logininforService.addLog(username, Constant.LOGIN_FAIL, "用户不存在");
            throw new UnknownAccountException("用户不存在");
        }
        if (sysUser.getUserState() == -1) {
            logininforService.addLog(username, Constant.LOGIN_FAIL, "用户已被禁用");
            throw new UnknownAccountException("用户已被禁用");
        }
        // 此处不再需要做密码验证，由CredentialsMatch的实现做密码校验
        /*if (!sysUser.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }*/
        if (sysUser.getUserState().equals(StateEnum.DISABLE.getState())) {
            logininforService.addLog(username, Constant.LOGIN_FAIL, "该用户已被锁定，请稍后再试");
            throw new LockedAccountException("该用户已被锁定，请稍后再试");
        }
        // 是否进行单一用户登陆处理
        boolean singeUserAuth = Boolean.parseBoolean(configService.getValueByKey("sys.user.singeUserAuth"));
        if (singeUserAuth) {
            Collection<Session> activeSessions = sessionDAO.getActiveSessions();
            for (Session activeSession : activeSessions) {
                if (activeSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
                    SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) activeSession
                            .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
                    User user = (User) primaryPrincipal;
                    if (user.getUserName().equals(username)) {
                        // 单一用户登出逻辑，是否强制登出前一用户
                        boolean singeKickoutBefore = Boolean.parseBoolean(configService.getValueByKey("sys.user.singeKickoutBefore"));
                        if (!singeKickoutBefore) {
                            logininforService.addLog(username, Constant.LOGIN_FAIL, "该用户已登陆，请先登出！");
                            throw new AuthenticationException("该用户已登陆，请先登出！");
                        }
                        Session session = sessionDAO.readSession(activeSession.getId());
                        if (session != null) {
                            session.stop();
                            sessionDAO.delete(session);
                            simpMessagingTemplate.convertAndSendToUser(user.getId(), "/queue/getResponse", "新消息：" + "该账号已在其他机器登陆！");
                        }
                    }
                }
            }
        }
        // 盐值加密
        ByteSource byteSource = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), byteSource, getName());
    }


    public void clearCachedAuthorizationInfo() {
        doClearCache(SecurityUtils.getSubject().getPrincipals());
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
