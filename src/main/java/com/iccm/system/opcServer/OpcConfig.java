package com.iccm.system.opcServer;

import com.iccm.common.properties.SystemProperties;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/10/16.
 */
@Component
public class OpcConfig {

    @Autowired
    private SystemProperties systemProperties;

    private boolean isConnect;

    private ConnectionInformation connectionInformation;

    private Server server;

    /**
     * 获取服务对象
     * @return
     * @throws AlreadyConnectedException
     * @throws JIException
     * @throws UnknownHostException
     */
    Server getServer() throws AlreadyConnectedException, JIException, UnknownHostException {
        if(server==null){
            server = new Server(getConnectionInformation(), Executors.newSingleThreadScheduledExecutor());
        }
        if(!isConnect){
            server.connect();
            isConnect = true;
        }
        return server;
    }

    /**
     * 断开服务连接
     */
    void disconnect(){
        server.disconnect();
        isConnect = false;
    }

    /**
     * 获取连接信息
     * @return
     */
    private ConnectionInformation getConnectionInformation(){
        if(connectionInformation==null){
            final ConnectionInformation ci = new ConnectionInformation();
            ci.setHost(systemProperties.getOpcHost());
            ci.setDomain(systemProperties.getOpcDomain());
            ci.setUser(systemProperties.getOpcUser());
            ci.setPassword(systemProperties.getOpcPassword());
            ci.setClsid(systemProperties.getOpcClsid());
            connectionInformation = ci;
        }
        return connectionInformation;
    }
}
