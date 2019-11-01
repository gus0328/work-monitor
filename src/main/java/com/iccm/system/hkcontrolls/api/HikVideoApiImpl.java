package com.iccm.system.hkcontrolls.api;

//import com.iking.device.module.video.VideoApi;
//import com.iking.device.module.video.bo.VideoConnectBo;
//import com.iking.device.module.video.enums.PtzControlEnum;
//import com.iking.device.module.video.hikvision.lib.HCNetSDK;
//import com.iking.device.module.video.hikvision.lib.HKNetSDK;
import com.iccm.system.hkcontrolls.lib.HCNetSDK;
import com.iccm.system.hkcontrolls.lib.HKNetSDK;
import com.sun.jna.NativeLong;
import lombok.extern.log4j.Log4j2;

/**
 * 海康摄像头实现类
 */
@Log4j2
public class HikVideoApiImpl{

//    VideoConnectBo connectByIp;
//
//
//    public NativeLong loginHandle = new NativeLong(-1);
//
//    /**
//     * 设备信息
//     */
//    public HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo = null;
//
//    /**
//     * 初始化函数
//     *
//     * @param connectByIp 连接信息
//     */
//    public HikVideoApiImpl(VideoConnectBo connectByIp) {
//        this.connectByIp = connectByIp;
//
//    }
//
//    @Override
//    public void printVideoApiObject() {
//        log.info("hikVideoApiImpl");
//    }
//
//    @Override
//    public void login() {
//        loginHandle = HKNetSDK.login(connectByIp.getIpAddress(), connectByIp.getPtzPort(), connectByIp.getUserName(), connectByIp.getPassword(), deviceInfo);
//    }
//
//    @Override
//    public void getChannelStream(String channelId) {
//        //视频流调用第三方程序此处不处理
//    }
//
//    @Override
//    public void ptzControl(String channelId, PtzControlEnum command,int iStop) {
//        NativeLong lChannel = new NativeLong(Long.valueOf(channelId));
//        HKNetSDK.ptzControl(loginHandle,lChannel,command,5,iStop);
//    }
//
//    @Override
//    public boolean heartBeat() {
//        //在海康sdk init处已设置断线重连等操作，还未想好如何在此处维持心跳，
//        //暂用登陆者id判断
//        log.warn("hikVideoApiImpl heart beat");
//        return loginHandle.longValue() != -1;
//    }
//
//    @Override
//    public void logout() {
//        HKNetSDK.logout(this.loginHandle);
//    }
}
