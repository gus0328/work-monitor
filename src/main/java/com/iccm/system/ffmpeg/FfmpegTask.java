package com.iccm.system.ffmpeg;

import cc.eguid.commandManager.CommandManager;
import cc.eguid.commandManager.CommandManagerImpl;
import com.iccm.system.model.MonitorDevice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/17.
 */
@Component
public class FfmpegTask {

   private CommandManager manager;

   private Map<String,String> runingTask;

    CommandManager getCommandManager(){
        if(manager==null){
            manager = new CommandManagerImpl();
        }
        return manager;
    }

    Map<String,String> getRuningTask(){
        if(runingTask==null){
            runingTask = new HashMap<>();
        }
        return runingTask;
    }

    /**
     * 添加任务
     * @param monitorDevice
     * @return
     */
   public String addTask(MonitorDevice monitorDevice){
       if(StringUtils.isBlank(getRuningTask().get(monitorDevice.getIpAdress()))){
           String command = "ffmpeg -i "+monitorDevice.getRtspUrl()+" -vcodec copy -acodec copy -f flv "+ monitorDevice.getRtmpUrl();
           getRuningTask().put(monitorDevice.getIpAdress(),monitorDevice.getRtmpUrl());
           getCommandManager().start(monitorDevice.getIpAdress(),command);
       }
       return getRuningTask().get(monitorDevice.getIpAdress());
   }

    /**
     * 停止任务
     * @param ipAddress
     */
   public void stopTask(String ipAddress){
       getCommandManager().stop(ipAddress);
   }
}
