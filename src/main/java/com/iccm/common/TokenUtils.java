package com.iccm.common;

import com.iccm.common.properties.TokenKey;
import com.iccm.common.utils.Md5Utils;
import com.iccm.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/9/10.
 */
@Component("tokenUtils")
public class TokenUtils {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TokenKey tokenKey;

    public String createPcToken(String userCode,String sessionId){
        return Md5Utils.hash(userCode+sessionId+tokenKey.getPcToken());
    }

    public String createAppToken(){
        return Md5Utils.hash(UUIDUtil.randomUUID32()+tokenKey.getAppToken());
    }
}
