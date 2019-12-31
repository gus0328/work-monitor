package com.iccm.system;


import com.iccm.common.utils.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.openscada.opc.lib.da.SyncAccess;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.Server;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/9/25.
 */
public class Test {

    public static void main(String[] args) {

//        final ConnectionInformation ci = new ConnectionInformation();
//        ci.setHost("192.168.120.118");
////        ci.setDomain("chinacoalmd.com");
//        ci.setDomain("");
//        ci.setUser("administrator");
//        ci.setPassword("Huawei12#$%^");
////        ci.setProgId("TLSvrRDK.OPCTOOLKIT.DEMO");
//        ci.setClsid("FFCED1F1-278E-11D5-A2B0-00C04F1BFD1B"); // if ProgId is not working, try it using the Clsid instead
//        final String itemId = "1190GI1102.DACA.PV";
//        final String itemId1 = "4000AI1100_1.DACA.PV";
//        // create a new server
//        ScheduledExecutorService scheduledExecutorService =Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule(() -> {}, 5000, TimeUnit.MILLISECONDS);
//        final Server server = new Server(ci, scheduledExecutorService);
//
//        try {
//            // connect to server
//            server.connect();
//            // add sync access, poll every 500 ms
//            final AccessBase access = new SyncAccess(server, 1000);
//            access.addItem(itemId, (item, state) ->{
//                System.out.println("result1--------------------------:"+state.getValue().toString());
//            });
//            // start reading
//            access.bind();
//            // wait a little bit
//            Thread.sleep(10 * 1000);
//
////           access.addItem(itemId1,(item, state) ->{
////               System.out.println("result2--------------------------:"+state.getValue().toString());
////           });
//            access.unbind();
//            access.clear();
//            server.disconnect();
////            final Server server1 = new Server(ci, scheduledExecutorService);
////            server1.connect();
////            // add sync access, poll every 500 ms
////            final AccessBase access1 = new SyncAccess(server1, 1000);
////            // stop reading
////            access1.addItem(itemId, (item, state) ->{
////                System.out.println("result2--------------------------:"+state.getValue().toString());
////            });
////            access1.bind();
////            access.removeItem(itemId);
////            access.connectionStateChanged(false);
////            Thread.sleep(10 * 1000);
////            access.unbind();
////            server.disconnect();
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(Float.parseFloat("80")/100);
//        Long n1 = 1l;
//        int num = n1.intValue();
//        System.out.println(num);
//        Date date = new Date();
//        Date date1 = DateUtil.parseDate("2019-11-25 00:00:00");
//        Date date2 = DateUtil.getAppointDate(date1,7);
//        int n =DateUtil.compareToDate(date2,date);
//        if(n!=1){
//            System.out.println(true);
//        }else{
//            System.out.println(false);
//        }
//        Float num = 12.23f;
//        System.out.println(num.intValue());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = "2019-12-30";
        try{
            double d = Double.parseDouble(date1);
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(Double.valueOf(43829+""));
            date1 = format.format(date);
        }catch (Exception e){
            //do not;
        }
        System.out.println(date1);
    }
}
