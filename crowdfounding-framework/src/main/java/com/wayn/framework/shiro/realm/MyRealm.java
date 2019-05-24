package com.wayn.framework.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.domain.User;
import com.wayn.service.RoleMenuService;
import com.wayn.service.UserRoleService;
import com.wayn.service.UserService;

public class MyRealm extends AuthorizingRealm {

	/**
	 * 用户服务
	 */
	@Autowired
	private UserService userService;
	/**
	 * 用户角色服务
	 */
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * 角色菜单服务
	 */
	@Autowired
	private RoleMenuService roleMenuService;

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
		String password = encrypt(username, new String(token2.getPassword()));
		User sysUser = userService.selectOne(new EntityWrapper<User>().eq("userName", username));
		if (sysUser == null) {
			throw new UnknownAccountException("用户不存在");
		}
		if (sysUser.getUserState() == -1) {
			throw new UnknownAccountException("用户已被禁用");
		}
		if (!sysUser.getPassword().equals(password)) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		if (sysUser.getUserState() == User._0) {
			throw new LockedAccountException("该用户已被锁定，请稍后再试");
		}
		//盐值加密
		ByteSource byteSource = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), byteSource,
				getName());
		return info;
	}

	public String encrypt(String username, String password) {
		return new SimpleHash("MD5", password, username, 1024).toString();
	}

	/**
	 * 密码加密 测试
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// MD5,"原密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "123456", "admin", 1024).toString();
		System.out.println(pwd);
	}

	public void clearCachedAuthorizationInfo() {
		clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
