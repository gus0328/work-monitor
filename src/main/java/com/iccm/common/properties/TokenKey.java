package com.iccm.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/9/10.
 */
@ConfigurationProperties(prefix = "iccm.token")
@Component("tokenKey")
@PropertySource("application-${iccm_env}.properties")
@Data
public class TokenKey {

    private String pcToken;
}
