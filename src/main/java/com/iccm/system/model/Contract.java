package com.iccm.system.model;

import com.iccm.common.utils.StringUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

import java.util.Date;

/**
 * 合同超期提醒对象 contract
 * 
 * @author gxj
 * @date 2019-09-17
 */
@Data
public class Contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractNo;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 供货商 */
    @Excel(name = "供货商")
    private String provideGoods;

    /** 合同到货日期 */
    @Excel(name = "合同到货日期")
    private String contractGoodsArrivalDate;

    /** 合同终止日期 */
    @Excel(name = "合同终止日期")
    private String contractStopDate;

    /** 合同执行情况 */
    @Excel(name = "合同执行情况")
    private String contractStatus;

    /** 累计入账额 */
    @Excel(name = "累计入账额")
    private String totalAccount;

    /** 累计付款额 */
    @Excel(name = "累计付款额")
    private String totalPay;

    /** 累计付款额提醒百分比 */
    @Excel(name = "累计付款额提醒百分比")
    private String payWarnBfb;

    /** 剩余付款额 */
    @Excel(name = "剩余付款额")
    private String noPay;

    /** 质保金 */
    @Excel(name = "质保金")
    private String premium;

    /** 归档日期 */
    @Excel(name = "归档日期")
    private String filingDate;

    /** 超期时间 */
    @Excel(name = "合同终止提醒时间")
    private String expiryDate;

    /** 提醒用户 */
    @Excel(name = "提醒用户")
    private String warnName;

    /** 状态(1已通知0未通知) */
    private Integer status;

    private Date latestWarnTime;

    /** 提醒间隔时间 */
    @Excel(name = "提醒间隔时间")
    private String warnTime;

    /** $column.columnComment */
    @Excel(name = "提前提醒天数")
    private String beforeWarnTime;

    @Override
    public String toString() {
        float realBfb = 0;
        if(StringUtils.isNotBlank(totalPay)&&StringUtils.isNotBlank(totalPay)){
            float totalPay1 = Float.parseFloat(totalPay);
            float noPay1 = Float.parseFloat(noPay);
            realBfb = totalPay1/(totalPay1+noPay1)*100;
        }
        return "合同详情:" +
                "合同编号：" + contractNo +
                "<br/> 合同名称：" + contractName +
                "<br/> 供货商：" + provideGoods+
                "<br/> 合同到货日期：" + contractGoodsArrivalDate  +
                "<br/> 合同终止日期：" + contractStopDate+
                "<br/> 合同执行情况：" + contractStatus+
                "<br/> 累计入账额：" + totalAccount +
                "<br/> 累计付款额：" + totalPay +
                "<br/> 剩余付款额：" + noPay +
                "<br/> 质保金：" + premium +
                "<br/> 付款百分比：" + realBfb+"%" +
                "<br/> 归档日期：" + filingDate ;
    }

    public String getNotice(){
        return "合同【"+contractNo+"】超期提醒,合同终止日期："+contractStopDate;
    }
}
