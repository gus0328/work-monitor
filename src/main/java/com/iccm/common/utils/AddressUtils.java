package com.iccm.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.SpringContextHolder;
import com.iccm.common.properties.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 * 
 * @author ruoyi
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";

        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (SpringContextHolder.getBean(SystemProperties.class).isAddressEnabled())
        {
            String rspStr = HttpUtils.sendPost(SpringContextHolder.getBean(SystemProperties.class).getIpUrl(), "ip=" + ip);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj;
            try
            {
                obj = JSONObject.parseObject(rspStr);
                JSONObject data = obj.getJSONObject("data");
                String region = data.getString("region");
                String city = data.getString("city");
                address = region + " " + city;
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
