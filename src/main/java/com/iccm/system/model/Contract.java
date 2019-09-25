package com.iccm.system.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 合同超期提醒对象 contract
 * 
 * @author gxj
 * @date 2019-09-17
 */
public class Contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractNo;

    /** 合同签订日期 */
    @Excel(name = "合同签订日期")
    private String contractDate;

    /** 超期时间 */
    @Excel(name = "超期时间")
    private String expiryDate;

    /** 提醒用户 */
    @Excel(name = "提醒用户")
    private String warnName;

    /** 状态(1已通知0未通知) */
    @Excel(name = "状态(1已通知0未通知)")
    private Integer status;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setContractDate(String contractDate) 
    {
        this.contractDate = contractDate;
    }

    public String getContractDate() 
    {
        return contractDate;
    }
    public void setExpiryDate(String expiryDate) 
    {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() 
    {
        return expiryDate;
    }
    public void setWarnName(String warnName) 
    {
        this.warnName = warnName;
    }

    public String getWarnName() 
    {
        return warnName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contractNo", getContractNo())
            .append("contractDate", getContractDate())
            .append("expiryDate", getExpiryDate())
            .append("warnName", getWarnName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
