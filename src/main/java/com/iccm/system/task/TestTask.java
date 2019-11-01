package com.iccm.system.task;

import com.iccm.system.mapper.GasDeviceMapper;
import com.iccm.system.mapper.WearDeviceMapper;
import com.iccm.system.opcServer.OpcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31.
 */
@Component
public class TestTask {

    @Autowired
    private OpcTask opcTask;

    @Autowired
    private GasDeviceMapper gasDeviceMapper;

    @Autowired
    private WearDeviceMapper wearDeviceMapper;

//    @Scheduled(cron = "0/5 * * * * *")
    public void test(){
        List<String> wearDeviceItemId = wearDeviceMapper.getRunningItemId();
        wearDeviceItemId.forEach(itemId ->{
            System.out.println(opcTask.get(itemId,String.class));
        });
        List<String> runningItemId = gasDeviceMapper.getRunningItemId();
        runningItemId.forEach(itemId ->{
            System.out.println(opcTask.get(itemId,String.class));
        });
    }
}
