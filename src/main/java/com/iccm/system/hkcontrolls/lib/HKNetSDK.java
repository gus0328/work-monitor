package com.iccm.system.hkcontrolls.lib;


//import com.iking.device.module.video.dahua.lib.ToolKits;
//import com.iking.device.module.video.enums.PtzControlEnum;

import com.iccm.common.utils.DateUtil;
import com.sun.jna.NativeLong;

import java.io.File;

import static com.iccm.system.hkcontrolls.lib.HCNetSDK.*;

/**
 * 海康设备api
 */
public class HKNetSDK {

    public static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

    /**
     * 初始化工作
     */
    public static void init() {
        hCNetSDK.NET_DVR_Init();
        int i = hCNetSDK.NET_DVR_GetSDKVersion();
        System.err.println("hk sdk version" + i);
        //设置自动重连
        hCNetSDK.NET_DVR_SetReconnect(10000, true);
        //设置日志
        File path = new File("./hksdklog/");
        if (!path.exists()) {
            path.mkdir();
        }
        String logPath = path.getAbsoluteFile().getParent() + "\\hksdklog\\" + DateUtil.getCurrentDate() + ".log";
        hCNetSDK.NET_DVR_SetLogToFile(true, logPath, true);

        //注册异常回调函数
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
    }

    /**
     * 登录
     */
    public static NativeLong login(String ipAddress, int port, String userName, String password, HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo) {
        //注册
        if(!hCNetSDK.NET_DVR_Init()){
            return new NativeLong(-1);
        }
        NativeLong nativeLong = hCNetSDK.NET_DVR_Login_V30(ipAddress,
                (short) port, userName, password, deviceInfo);
        if (nativeLong.intValue() < 0) {
            int iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println( "：注册失败,错误号：" + iErr);
        }
        return nativeLong;
    }

    /**
     * 云台控制
     * @param lUserID 登录id
     * @param lChannel 通道号
     * @param dwSpeed 速度
     * @param iStop  云台停止动作或开始动作：0－开始，1－停止
     * @return
     */
    public static boolean ptzControl(NativeLong lUserID, NativeLong lChannel,int command, int dwSpeed, int iStop) {
        //控制命令
        int iPTZCommand = 0;
        switch (command) {
            case 0:
                iPTZCommand = UP_LEFT;
                break;
            case 1:
                iPTZCommand = TILT_DOWN;
                break;
            case 2:
                iPTZCommand = UP_RIGHT;
                break;
            case 3:
                iPTZCommand = DOWN_LEFT;
                break;
            case 4:
                iPTZCommand = TILT_UP;
                break;
            case 5:
                iPTZCommand = DOWN_RIGHT;
                break;
            case 6:
                iPTZCommand = ZOOM_IN;//放大
                break;
            case 7:
                iPTZCommand = ZOOM_OUT;//缩小
                break;
            default:
                break;
        }

        return hCNetSDK.NET_DVR_PTZControlWithSpeed_Other(lUserID, lChannel, iPTZCommand, iStop, dwSpeed);
    }
    /**
     * 注销
     */
    public static void logout(NativeLong loginHandle) {
        hCNetSDK.NET_DVR_Logout(loginHandle);
    }

    /**
     * 一般在应用程序关闭时调用， SDK退出，清理并释放资源。
     * 不建议用户在没使用完SDK功能期间多次调用CLIENT_Init和该函数。
     */
    public static boolean cleanup() {
        return hCNetSDK.NET_DVR_Cleanup();
    }

}
