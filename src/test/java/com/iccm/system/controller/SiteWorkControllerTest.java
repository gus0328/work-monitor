package com.iccm.system.controller;

import com.iccm.WorkMonitorApplication;
import com.iccm.common.utils.DateUtil;
import com.iccm.system.mapper.SiteGasMapper;
import com.iccm.system.mapper.SiteMonitorMapper;
import com.iccm.system.mapper.SiteWorkerMapper;
import com.iccm.system.model.*;
import com.iccm.system.service.ISiteWorkService;
import com.iccm.system.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/9/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkMonitorApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SiteWorkControllerTest {

    @Autowired
    private ISiteWorkService siteWorkService;

    @Autowired
    private SiteWorkerMapper siteWorkerMapper;

    @Autowired
    private SiteGasMapper siteGasMapper;

    @Autowired
    private SiteMonitorMapper siteMonitorMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void addSave() throws Exception {
        RequestContextHolder.setRequestAttributes(new ServletWebRequest(new MockHttpServletRequest()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("user",sysUserService.selectUserById(1L));
        for(int i = 0;i < 100;i++){
            SiteWorkVo siteWorkVo = new SiteWorkVo();
            SiteWork siteWork = new SiteWork();
            siteWork.setLeadUserId(101L);
            siteWork.setLeadUserName("高晓静");
            siteWork.setWorkSite("作业地点1");
            siteWork.setStartTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
            siteWork.setWorkStatus(0);
            siteWorkVo.setSiteWork(siteWork);
            List<SiteWorkerVo> workers = siteWorkerMapper.getWorkerListByWorkId("ZY2019092516450101");
            workers.forEach(siteWorkerVo -> {
                siteWorkerVo.setDevices(siteWorkerMapper.getDeviceList(siteWorkerVo));
            });
            List<SiteMonitor> monitors = siteMonitorMapper.getMonitorsByWorkId("ZY2019092516450101");
            List<SiteGas> gases = siteGasMapper.getGasesByWorkId("ZY2019092516450101");
            siteWorkVo.setWorkers(workers);
            siteWorkVo.setGases(gases);
            siteWorkVo.setMonitors(monitors);
            siteWorkService.insertSiteWork(siteWorkVo);
        }
    }
}