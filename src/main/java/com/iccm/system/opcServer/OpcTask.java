package com.iccm.system.opcServer;

import com.iccm.common.CacheName;
import com.iccm.common.properties.SystemProperties;
import com.iccm.system.mapper.SiteGasMapper;
import com.iccm.system.mapper.SiteWorkerMapper;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.SyncAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/16.
 */
@Component
public class OpcTask {

    @Autowired
    private OpcConfig opcConfig;

    @Autowired
    private CacheManager cacheManager;

    private AccessBase accessBase;

    private Map<String,Boolean> itemMap;

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private SiteWorkerMapper siteWorkerMapper;

    @Autowired
    private SiteGasMapper siteGasMapper;

    /**
     * 添加
     * @param itemId
     */
    public void addNewItem(String itemId) {
        try{
            if(accessBase==null){
                accessBase = new SyncAccess(opcConfig.getServer(), systemProperties.getOpcMillis());
            }
            if(itemMap==null){
                itemMap = new HashMap<>();
            }
            if(itemMap.get(itemId)==null){
                itemMap.put(itemId,true);
                accessBase.addItem(itemId, (item, state) ->{
                    if(state.getErrorCode()==0){
                        String valueStr = state.getValue().toString();
                        cacheManager.getCache(CacheName.OPCDATA).put(itemId,valueStr.substring(2,valueStr.length()-2));
                    }
                });
                // start reading
                accessBase.bind();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param itemId
     */
    public int removeItem(String itemId,OpcTypeEnum opcTypeEnum){
        switch (opcTypeEnum){
            case siteGas:
                if(siteGasMapper.verifyDeviceIfRunning(itemId)>0) {
                    return -1;
                }
                break;
            case siteWorker:
                if(siteWorkerMapper.verifyDeviceIfRunning(itemId)>0){
                    return -1;
                }
                break;
        }
        try{
            accessBase.removeItem(itemId);
            cacheManager.getCache(CacheName.OPCDATA).evict(itemId);
            itemMap.remove(itemId);
            if(itemMap.size()==0){
                accessBase.unbind();
                accessBase.clear();
                accessBase.connectionStateChanged(false);
                accessBase = null;
                opcConfig.disconnect();
            }
            return 1;
        }catch (Exception e){
            //do nothing
            return -2;
        }
    }

    /**
     * 获取数据
     * @param itemId
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String itemId,Class<T> clazz){
        try{
            return cacheManager.getCache(CacheName.OPCDATA).get(itemId,clazz);
        }catch (Exception e){
            return null;
        }
    }
}
