package com.iccm.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 现场作业对象 site_work
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Data
public class SiteWork extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 负责人id */
    @Excel(name = "负责人id")
    private Long leadUserId;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String leadUserName;
    /** 作业地点 */
    @Excel(name = "作业地点")

    private String workSite;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    /** 作业状态(0未启动1已启动2暂停3结束) */
    @Excel(name = "作业状态(0未启动1已启动2暂停3结束)")
    private Integer workStatus;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    private String spareWord1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String spareWord2;

    /** 备用字段3 */
    @Excel(name = "备用字段3")
    private String spareWord3;

}
