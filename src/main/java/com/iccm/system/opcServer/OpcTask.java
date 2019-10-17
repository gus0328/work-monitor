package com.iccm.system.opcServer;

import com.iccm.common.CacheName;
import com.iccm.common.properties.SystemProperties;
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

    private Map<String,AccessBase> itemMap;

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 添加
     * @param itemId
     */
    public void addNewItem(String itemId) {
        try{
            if(itemMap==null){
                itemMap = new HashMap<>();
            }
            if(itemMap.get(itemId)==null){
                final AccessBase access = new SyncAccess(opcConfig.getServer(), systemProperties.getOpcMillis());
                itemMap.put(itemId,access);
                access.addItem(itemId, (item, state) ->{
                    if(state.getErrorCode()==0){
                        String valueStr = state.getValue().toString();
                        cacheManager.getCache(CacheName.OPCDATA).put(itemId,valueStr.substring(2,valueStr.length()-2));
                        System.out.println(get("4000AI1100_1.DACA.PV",String.class));
                    }
                });
                // start reading
                access.bind();
            }
        }catch (Exception e){
            //do nothing
        }
    }

    /**
     * 删除
     * @param itemId
     */
    public void removeItem(String itemId){
        try{
            AccessBase accessBase = itemMap.get(itemId);
            accessBase.unbind();
            cacheManager.getCache(CacheName.OPCDATA).evict(itemId);
            itemMap.remove(itemId);
            if(itemMap.size()==0){
                opcConfig.getServer().disconnect();
            }
        }catch (Exception e){
            //do nothing
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
        return cacheManager.getCache(CacheName.OPCDATA).get(itemId,clazz);
    }
}
