package com.iccm.system.model;

import lombok.Data;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author gxj
 */
@Data
public class SysUserPost
{
    /** 用户ID */
    private Long userId;
    
    /** 岗位ID */
    private Long postId;

}
