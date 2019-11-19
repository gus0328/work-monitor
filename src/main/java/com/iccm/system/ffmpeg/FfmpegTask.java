package com.iccm.system.ffmpeg;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/10/17.
 */
@Component
public class FfmpegTask {

//   private CommandManager manager;
//
//   private Map<String,String> runingTask;
//
//   @Autowired
//   private SystemProperties systemProperties;
//
//   @Autowired
//   private SiteMonitorMapper siteMonitorMapper;
//
//    CommandManager getCommandManager(){
//        if(manager==null){
//            manager = new CommandManagerImpl();
//        }
//        return manager;
//    }
//
//    Map<String,String> getRuningTask(){
//        if(runingTask==null){
//            runingTask = new HashMap<>();
//        }
//        return runingTask;
//    }
//
//    /**
//     * 添加任务
//     * @param monitorDevice
//     * @return
//     */
//   public String addTask(MonitorDevice monitorDevice){
//       if(StringUtils.isBlank(getRuningTask().get(monitorDevice.getIpAdress()))){
//           ApplicationListener.executorService.submit(()->{
//               String command = "ffmpeg -i "+monitorDevice.getRtspUrl()+" -c copy -f hls -hls_time 2.0 -hls_list_size 0 -hls_wrap 8 "+systemProperties.getHlsPath()+ monitorDevice.getHlsUrl();
//               getRuningTask().put(monitorDevice.getIpAdress(),monitorDevice.getHlsUrl());
//               getCommandManager().start(monitorDevice.getIpAdress(),command);
//           });
//       }
//       return getRuningTask().get(monitorDevice.getIpAdress());
//   }
//
//    /**
//     * 停止任务
//     * @param ipAddress
//     */
//   public void stopTask(String ipAddress){
//       if(siteMonitorMapper.verifyDeviceIfRunning(ipAddress)<1){
//           getCommandManager().stop(ipAddress);
//           getRuningTask().remove(ipAddress);
//           if(getRuningTask().size()==0){
//               manager.destory();
//               manager = null;
//           }
//       }
//   }
}
