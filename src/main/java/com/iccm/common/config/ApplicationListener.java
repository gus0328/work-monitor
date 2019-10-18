package com.iccm.common.config;

import com.iccm.system.ffmpeg.FfmpegTask;
import com.iccm.system.model.MonitorDevice;
import com.iccm.system.opcServer.OpcTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:<监听项目启动>
 * @Data 2017年09月14日 09:23
 * @Author:GXJ
 */
@Component
public class ApplicationListener implements ServletContextListener {

    private final static Logger log = LoggerFactory.getLogger(ApplicationListener.class);

    public static ServletContext Application;

    public static ExecutorService executorService;

    @Autowired
    private OpcTask opcTask;

    @Autowired
    private FfmpegTask ffmpegTask;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(Application==null){
            Application = servletContextEvent.getServletContext();
            executorService = Executors.newFixedThreadPool(20);
            log.warn("- - - - - - - - 已初始化ServletContext- - - - - - - - - - ");
            MonitorDevice monitorDevice = new MonitorDevice();
            monitorDevice.setItemCode("admin");
            monitorDevice.setItemName("admin123");
            monitorDevice.setIpAdress("192.168.102.89");
            monitorDevice.setSpareWord1("554");
            MonitorDevice monitorDevice1 = new MonitorDevice();
            monitorDevice1.setItemCode("admin");
            monitorDevice1.setItemName("admin123");
            monitorDevice1.setIpAdress("192.168.102.88");
            monitorDevice1.setSpareWord1("554");
            ffmpegTask.addTask(monitorDevice);
            ffmpegTask.addTask(monitorDevice1);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdown();
    }

}
