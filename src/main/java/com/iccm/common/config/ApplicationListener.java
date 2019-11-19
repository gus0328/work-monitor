package com.iccm.common.config;

import com.iccm.system.ffmpeg.FfmpegTask;
import com.iccm.system.mapper.GasDeviceMapper;
import com.iccm.system.mapper.MonitorDeviceMapper;
import com.iccm.system.mapper.WearDeviceMapper;
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
import java.util.List;
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

    @Autowired
    private MonitorDeviceMapper monitorDeviceMapper;

    @Autowired
    private GasDeviceMapper gasDeviceMapper;

    @Autowired
    private WearDeviceMapper wearDeviceMapper;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(Application==null){
            Application = servletContextEvent.getServletContext();
            executorService = Executors.newFixedThreadPool(20);
            log.warn("- - - - - - - - 已初始化ServletContext- - - - - - - - - - ");
//            List<MonitorDevice> runningDevice = monitorDeviceMapper.getRunningDevice();
//            runningDevice.forEach(monitorDevice -> {
//                ffmpegTask.addTask(monitorDevice);
//            });
            List<String> wearDeviceItemId = wearDeviceMapper.getRunningItemId();
            wearDeviceItemId.forEach(itemId ->{
                opcTask.addNewItem(itemId);
            });
            List<String> runningItemId = gasDeviceMapper.getRunningItemId();
            runningItemId.forEach(itemId ->{
                opcTask.addNewItem(itemId);
            });
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdown();
    }

}
