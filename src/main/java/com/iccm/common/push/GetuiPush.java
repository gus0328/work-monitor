package com.iccm.common.push;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.iccm.common.properties.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/29.
 */
@Component
public class GetuiPush {

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 打开应用首页模板
     * @param title 通知栏标题
     * @param content 通知栏内容
     * @param data 透传内容
     * @return
     */
    public NotificationTemplate notificationTemplate(String title,String content,String data) {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(systemProperties.getAppId());
        template.setAppkey(systemProperties.getAppKey());
        template.setTransmissionType(1);
        template.setTransmissionContent(data);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
//        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl(systemProperties.getIcon());
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
//        style.setChannel("自定义channel");
//        style.setChannelName("自定义channelName");
//        style.setChannelLevel(3);
        template.setStyle(style);
        return template;
    }

    /**
     * 点击打开网址
     * @param title
     * @param content
     * @param url
     * @return
     */
    public LinkTemplate openUrlTemplate(String title,String content,String url) {
        LinkTemplate template = new LinkTemplate();
        template.setAppId(systemProperties.getAppId());
        template.setAppkey(systemProperties.getAppKey());

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
//        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl(systemProperties.getIcon());
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
//        style.setChannel("自定义channel");
//        style.setChannelName("自定义channelName");
        style.setChannelLevel(3);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(url);
        // 设置定时展示时间，安卓机型可用
        // template.setDuration("2019-08-16 11:40:00", "2019-08-16 12:24:00");

        // 消息覆盖
        // template.setNotifyid(123); // 在消息推送的时候设置自定义的notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。
        return template;
    }

    /**
     * 获取模板
     * @param templateType
     * @return
     */
    public ITemplate getTemplate(String title,String content,String dataOrUrl,TemplateType templateType){
        switch (templateType){
            case notice:
                return notificationTemplate(title,content,dataOrUrl);
            case openUrl:
                return openUrlTemplate(title,content,dataOrUrl);
            default:
                return null;
        }
    }

    /**
     * 推送个人
     * @param cid
     * @param title
     * @param content
     * @param dataOrUrl
     */
    public void pushtoSingle(String cid,String title,String content,String dataOrUrl,TemplateType templateType){
        IGtPush push = new IGtPush(systemProperties.getGetuiHost(), systemProperties.getAppKey(), systemProperties.getMasterSecret());
        ITemplate template = getTemplate(title,content,dataOrUrl,templateType);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(systemProperties.getAppId());
        target.setClientId(cid);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    /**
     * 组装targets
     * @param cids
     * @return
     */
    public List<Target> getTargets(List<String> cids){
        List<Target> targets = new ArrayList<>();
        cids.forEach(cid ->{
            Target target = new Target();
            target.setAppId(systemProperties.getAppId());
            target.setClientId(cid);
            targets.add(target);
        });
        return targets;
    }

    /**
     * 批量推送
     * @param cids
     * @param title
     * @param content
     * @param dataOrUrl
     * @param templateType
     */
    public void pushToList(List<String> cids,String title,String content,String dataOrUrl,TemplateType templateType){
        IGtPush push = new IGtPush(systemProperties.getGetuiHost(),systemProperties.getAppKey(), systemProperties.getMasterSecret());
        // 通知透传模板
        ITemplate template = getTemplate(title,content,dataOrUrl,templateType);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = getTargets(cids);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 推送给所有人
     * @param title
     * @param content
     * @param dataOrUrl
     * @param templateType
     * @param taskName
     */
    public void pushToApp(String title,String content,String dataOrUrl,TemplateType templateType,String taskName){
        IGtPush push = new IGtPush(systemProperties.getGetuiHost(), systemProperties.getAppKey(), systemProperties.getMasterSecret());
        ITemplate template = getTemplate(title,content,dataOrUrl,templateType);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setOffline(true);
        // 离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 推送给App的目标用户需要满足的条件
//        AppConditions cdt = new AppConditions();
//        List<String> appIdList = new ArrayList<String>();
//        appIdList.add(systemProperties.getAppId());
//        message.setAppIdList(appIdList);
        // 手机类型
//        List<String> phoneTypeList = new ArrayList<String>();
//        phoneTypeList.add("IOS");
//        phoneTypeList.add("ANDROID");
        // 省份
//        List<String> provinceList = new ArrayList<String>();
        // 参见region_code.data
//        provinceList.add("33010000");//杭州市
//        provinceList.add("51010000");//成都市
        // 自定义tag
//        List<String> tagList = new ArrayList<String>();
//        tagList.add("tag001");

        // 查询可推送的用户画像(需要开通VIP套餐)
//        IQueryResult personaTagResult = push.getPersonaTags(APPID);
//        System.out.println(personaTagResult.getResponse());
        // 工作
//        List<String> jobs = new ArrayList<String>();
//        jobs.add("0102");
//        jobs.add("0110");
//        conditions.addCondition("job", jobs);
//        // 年龄
//        List<String> age = new ArrayList<String>();
//        age.add("0000");
//        conditions.addCondition("age", age);

//        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList, AppConditions.OptType.or);
//        cdt.addCondition(AppConditions.REGION, provinceList, AppConditions.OptType.or);
//        cdt.addCondition(AppConditions.TAG,tagList, AppConditions.OptType.and);
//        cdt.addCondition("job", jobs, AppConditions.OptType.not);
//        cdt.addCondition("age", age);
//        message.setConditions(cdt);

        IPushResult ret = push.pushMessageToApp(message,taskName);
//        System.out.println(ret.getResponse().toString());
    }
}
