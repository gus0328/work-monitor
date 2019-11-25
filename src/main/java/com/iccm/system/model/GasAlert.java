package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

/**
 * Created by Administrator on 2019/11/22.
 */
@Data
public class GasAlert {

    private Long id;

    /** 气体类型 */
    @Excel(name = "气体类型")
    private String gasType;

    /** 大于等于 */
    @Excel(name = "大于等于")
    private String thanNumber;

    /** 小于等于 */
    @Excel(name = "小于等于")
    private String lessNumber;

}
