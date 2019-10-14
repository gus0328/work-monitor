package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 反馈意见对象 feedback
 * 
 * @author gxj
 * @date 2019-10-10
 */
@Data
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 主题 */
    @Excel(name = "主题")
    private String title;

    /** 意见建议 */
    @Excel(name = "意见建议")
    private String content;

    /** 回复意见 */
    @Excel(name = "回复意见")
    private String replyContent;

    /** 状态(0已提交,1管理员已查看,2提交人未查看,3提交人已查看) */
    @Excel(name = "状态(0已提交,1管理员已查看,2提交人未查看,3提交人已查看)")
    private Long status;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createById;

    /** 部门 */
    @Excel(name = "部门")
    private String detpName;
}
