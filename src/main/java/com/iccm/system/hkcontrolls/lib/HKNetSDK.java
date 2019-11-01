package com.iccm.system.hkcontrolls.lib;


//import com.iking.device.module.video.dahua.lib.ToolKits;
//import com.iking.device.module.video.enums.PtzControlEnum;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import lombok.extern.log4j.Log4j;

import java.io.File;

//import static com.iking.device.module.video.hikvision.lib.HCNetSDK.*;

/**
 * 海康设备api
 */
public class HKNetSDK {

//    public static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
//
//    /**
//     * 初始化工作
//     */
//    public static void init() {
//        hCNetSDK.NET_DVR_Init();
//        int i = hCNetSDK.NET_DVR_GetSDKVersion();
//        System.err.println("hk sdk version" + i);
//        //设置自动重连
//        hCNetSDK.NET_DVR_SetReconnect(10000, true);
//        //设置日志
//        File path = new File("./hksdklog/");
//        if (!path.exists()) {
//            path.mkdir();
//        }
//        String logPath = path.getAbsoluteFile().getParent() + "\\hksdklog\\" + ToolKits.getDate() + ".log";
//        hCNetSDK.NET_DVR_SetLogToFile(true, logPath, true);
//
//        //注册异常回调函数
//        hCNetSDK.NET_DVR_SetExceptionCallBack_V30(0, 0, new HCNetSDK.FExceptionCallBack() {
//            @Override
//            public void invoke(int dwType, NativeLong lUserID, NativeLong lHandle, Pointer pUser) {
//                switch (dwType) {
//                    case EXCEPTION_AUDIOEXCHANGE:        //语音对讲时网络异常
//                        log.error("语音对讲时网络异常!!!");
//                        //TODO: 关闭语音对讲
//                        break;
//                    case EXCEPTION_ALARM:            //报警上传时网络异常
//                        log.error("报警上传时网络异常!!!");
//                        //TODO: 关闭报警上传
//                        break;
//                    case EXCEPTION_PREVIEW:            //网络预览时异常
//                        log.error("网络预览时网络异常!!!");
//                        //TODO: 关闭网络预览
//                        break;
//                    case EXCEPTION_SERIAL:            //透明通道传输时异常
//                        log.error("透明通道传输时网络异常!!!");
//                        break;
//                    case EXCEPTION_RECONNECT:            //预览时重连
//                        log.error("预览时重连!!!");
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }, null);
//    }
//
//    /**
//     * 登录
//     */
//    public static NativeLong login(String ipAddress, int port, String userName, String password, HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo) {
//        //注册
//        NativeLong nativeLong = hCNetSDK.NET_DVR_Login_V30(ipAddress,
//                (short) port, userName, password, deviceInfo);
//        return nativeLong;
//    }
//
//    /**
//     * 云台控制
//     * @param lUserID 登录id
//     * @param lChannel 通道号
//     * @param dwSpeed 速度
//     * @param iStop  云台停止动作或开始动作：0－开始，1－停止
//     * @return
//     */
//    public static boolean ptzControl(NativeLong lUserID, NativeLong lChannel, PtzControlEnum command, int dwSpeed, int iStop) {
//        //控制命令
//        int iPTZCommand = 0;
//        switch (command) {
//            case UP:
//                iPTZCommand = TILT_UP;
//                break;
//            case DOWN:
//                iPTZCommand = TILT_DOWN;
//                break;
//            case LEFT:
//                iPTZCommand = PAN_LEFT;
//                break;
//            case RIGHT:
//                iPTZCommand = PAN_RIGHT;
//                break;
//            case ZOOM_ADD:
//                iPTZCommand = ZOOM_IN;
//                break;
//            case ZOOM_DEC:
//                iPTZCommand = ZOOM_OUT;
//                break;
//            default:
//                break;
//        }
//        return hCNetSDK.NET_DVR_PTZControlWithSpeed_Other(lUserID, lChannel, iPTZCommand, iStop, dwSpeed);
//    }
//    /**
//     * 注销
//     */
//    public static void logout(NativeLong loginHandle) {
//        hCNetSDK.NET_DVR_Logout(loginHandle);
//    }
//
//    /**
//     * 一般在应用程序关闭时调用， SDK退出，清理并释放资源。
//     * 不建议用户在没使用完SDK功能期间多次调用CLIENT_Init和该函数。
//     */
//    public static boolean cleanup() {
//        return hCNetSDK.NET_DVR_Cleanup();
//    }

}
