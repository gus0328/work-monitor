package com.iccm.system.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/9/23.
 */
@Data
public class SiteWorkVo {

    /**
     * 作业基本信息
     */
    private SiteWork siteWork;

    /**
     * 作业人员
     */
    private List<SiteWorkerVo> workers;

    /**
     * 气体监测设备
     */
    private List<SiteGas> gases;

    /**
     * 视屏监控设备
     */
    private List<SiteMonitor> monitors;
}
