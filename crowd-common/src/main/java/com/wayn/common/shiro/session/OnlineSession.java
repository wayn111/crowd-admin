package com.wayn.common.shiro.session;

import com.wayn.common.enums.OnlineStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 在线用户会话属性
 *
 * @author ruoyi
 */
@Getter
@Setter
public class OnlineSession extends SimpleSession {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 在线状态
     */
    private Integer status = OnlineStatusEnum.ON_LINE.getType();
}
