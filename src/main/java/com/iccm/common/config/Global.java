package com.iccm.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/9/5.
 */
@Component
@ConfigurationProperties(prefix = "com.iccm.zmmd")
public class Global {

    private String downPath;

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }
}
