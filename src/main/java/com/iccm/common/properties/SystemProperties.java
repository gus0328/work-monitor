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
@PropertySource("system.properties")
@Data
public class SystemProperties {

    private int sessionTime;

    private String initPass;
}
