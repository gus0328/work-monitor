package com.iccm.system.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/9.
 */
@Data
public class RoleAuthorityTree {

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 模块/接口名称
     */
    private String authorityName;

    /**
     * 接口url
     */
    private String parentUrl;

    /**
     * 角色id
     */
    private long roleId;

    /**
     * 状态（true该角色有此权限,false该角色没有此权限）
     */
    private boolean flag;

}
