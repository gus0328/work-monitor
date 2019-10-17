package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 移动设备管理对象 mobile_device
 * 
 * @author gxj
 * @date 2019-10-14
 */
@Data
public class MobileDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 设备id */
    @Excel(name = "设备id")
    private String deviceId;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 版本号 */
    @Excel(name = "版本号")
    private String systemVersion;

    /** 是否越狱(0未越狱，1已越狱) */
    @Excel(name = "是否越狱(0未越狱，1已越狱)")
    private String isRoot;

    /** 设备状态（0启用，1禁用） */
    @Excel(name = "设备状态", readConverterExp = "0=启用，1禁用")
    private Integer deviceStatus;

    /** 用户登录名称 */
    @Excel(name = "用户登录名称")
    private String loginName;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;
}
