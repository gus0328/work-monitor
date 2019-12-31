package com.iccm.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/9/11.
 */
@ConfigurationProperties(prefix = "com.iccm")
@Component
@PropertySource("classpath:system.properties")
@Data
public class SystemProperties {

    private int sessionTime;

    private String initPass;

    private String downPath;

    private boolean addressEnabled;

    private String ipUrl;

    private int appTokenTime;

    private String avatorPath;

    private String opcHost;

    private String opcDomain;

    private String opcUser;

    private String opcPassword;

    private String opcClsid;

    private int opcMillis;

    private String hlsPath;

    private String appId;

    private String appKey;

    private String appSecret;

    private String masterSecret;

    private String getuiHost;

    private String icon;

    private String warnTime;

    private String beforeWarnTime;
}
