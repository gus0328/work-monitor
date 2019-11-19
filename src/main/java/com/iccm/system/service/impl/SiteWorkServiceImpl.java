package com.iccm.system.service.impl;

import com.iccm.common.*;
import com.iccm.common.utils.DateUtil;
import com.iccm.system.ffmpeg.FfmpegTask;
import com.iccm.system.mapper.*;
import com.iccm.system.model.*;
import com.iccm.system.opcServer.OpcTask;
import com.iccm.system.opcServer.OpcTypeEnum;
import com.iccm.system.service.ISiteWorkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 现场作业Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class SiteWorkServiceImpl implements ISiteWorkService 
{
    @Autowired
    private SiteWorkMapper siteWorkMapper;

    @Autowired
    private SiteWorkerMapper siteWorkerMapper;

    @Autowired
    private SiteMonitorMapper siteMonitorMapper;

    @Autowired
    private SiteGasMapper siteGasMapper;

    @Autowired
    private WearDeviceMapper wearDeviceMapper;

    @Autowired
    private GasDeviceMapper gasDeviceMapper;

    @Autowired
    private MonitorDeviceMapper monitorDeviceMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OpcTask opcTask;

    private static final String WORKID ="workId";

    /**
     * 查询现场作业
     * 
     * @param id 现场作业ID
     * @return 现场作业
     */
    @Override
    public SiteWork selectSiteWorkById(String id)
    {
        return siteWorkMapper.selectSiteWorkById(id);
    }

    /**
     * 查询现场作业列表
     * 
     * @param siteWork 现场作业
     * @return 现场作业
     */
    @Override
    public List<SiteWork> selectSiteWorkList(SiteWork siteWork)
    {
    return siteWorkMapper.selectSiteWorkList(siteWork);
}

    /**
     * 新增现场作业
     * 
     * @param siteWorkVo 现场作业
     * @return 结果
     */
    @Transactional
    @Override
    public void insertSiteWork(SiteWorkVo siteWorkVo)
    {
        SiteWork siteWork = siteWorkVo.getSiteWork();
        siteWork.setId(createWorkId());
        siteWork.setCreateBy(SysUtils.getSysUser().getUserName());
        siteWork.setCreateTime(DateUtils.getNowDate());
        siteWorkMapper.insertSiteWork(siteWork);
        List<SiteWorkerVo> workers = siteWorkVo.getWorkers();
        List<SiteGas> gases = siteWorkVo.getGases();
        List<SiteMonitor> monitors = siteWorkVo.getMonitors();
        insertSiteWorkers(workers,siteWork);
        insertSiteGases(gases,siteWork);
        insertSiteMonitors(monitors,siteWork);
    }

    /**
     * 修改现场作业
     * 
     * @param siteWorkVo 现场作业
     * @return 结果
     */
    @Transactional
    @Override
    public void updateSiteWork(SiteWorkVo siteWorkVo)
    {
        SiteWork siteWork = siteWorkVo.getSiteWork();
        List<SiteWorkerVo> workers = siteWorkVo.getWorkers();
        List<SiteGas> gases = siteWorkVo.getGases();
        List<SiteMonitor> monitors = siteWorkVo.getMonitors();
        siteWorkerMapper.deleteSiteWorkerByWorkId(siteWork.getId());
        siteMonitorMapper.deleteSiteMonitorByWorkId(siteWork.getId());
        siteGasMapper.deleteSiteGasByWorkId(siteWork.getId());
        SiteWork updateSiteWork = siteWorkMapper.selectSiteWorkById(siteWork.getId());
        int status = updateSiteWork.getWorkStatus();
        switch (status){
            case 0:
                updateSiteWork = siteWork;
                break;
            case 1:
            case 2:
                if(siteWork.getWorkStatus()!=0){
                    updateSiteWork.setWorkStatus(siteWork.getWorkStatus());
                }
                break;
            case 3:
        }
        if(status!=3){
            updateSiteWork.setUpdateBy(SysUtils.getSysUser().getUserName());
            updateSiteWork.setUpdateTime(DateUtils.getNowDate());
            if(updateSiteWork.getWorkStatus()==3){
                updateSiteWork.setEndTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
            }
            siteWorkMapper.updateSiteWork(updateSiteWork);
            insertSiteWorkers(workers,siteWork);
            insertSiteGases(gases,siteWork);
            insertSiteMonitors(monitors,siteWork);
        }
    }

    /**
     * 删除现场作业对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteWorkByIds(String ids)
    {
        return siteWorkMapper.deleteSiteWorkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除现场作业信息
     * 
     * @param id 现场作业ID
     * @return 结果
     */
    @Transactional
    public JsonResult deleteSiteWorkById(String id)
    {
        SiteWork siteWork = siteWorkMapper.selectSiteWorkById(id);
        if(siteWork.getWorkStatus()>0){
            return JsonResult.error("作业已开始不能删除");
        }
        siteWorkerMapper.deleteSiteWorkerByWorkId(id);
        siteGasMapper.deleteSiteGasByWorkId(id);
        siteMonitorMapper.deleteSiteMonitorByWorkId(id);
        siteWorkMapper.deleteSiteWorkById(id);
        return JsonResult.ok();
    }

    /**
     * 根据id查询作业详情
     * @param id
     * @return
     */
    @Override
    public SiteWorkVo getWorkDetailsByWorkId(String id) {
        SiteWorkVo siteWorkVo = new SiteWorkVo();
        siteWorkVo.setSiteWork(siteWorkMapper.selectSiteWorkById(id));
        List<SiteWorkerVo> workers = siteWorkerMapper.getWorkerListByWorkId(id);
        workers.forEach(siteWorkerVo -> {
           siteWorkerVo.setDevices(siteWorkerMapper.getDeviceList(siteWorkerVo));
        });
        List<SiteMonitor> monitors = siteMonitorMapper.getMonitorsByWorkId(id);
        List<SiteGas> gases = siteGasMapper.getGasesByWorkId(id);
        siteWorkVo.setWorkers(workers);
        siteWorkVo.setGases(gases);
        siteWorkVo.setMonitors(monitors);
        return siteWorkVo;
    }

    public void insertSiteWorkers(List<SiteWorkerVo> workers,SiteWork siteWork){
        long index = 0;
        for(SiteWorkerVo siteWorkerVo:workers) {
            for(SiteWorkerVo.WearDevice device:siteWorkerVo.getDevices()){
                SiteWorker siteWorker = new SiteWorker(siteWorkerVo.getPersonName(),siteWorkerVo.getMobileNum(),siteWork.getId(),device.getWearDeviceId(),device.getItemCode(),device.getItemName(),index,device.getSpareWord1());
                if(siteWork.getWorkStatus()==1){
                    opcTask.addNewItem(device.getItemCode());
                }else if(siteWork.getWorkStatus()==2||siteWork.getWorkStatus()==3){
                    opcTask.removeItem(device.getItemCode(), OpcTypeEnum.siteWorker);
                }
                siteWorkerMapper.insertSiteWorker(siteWorker);
            }
            index++;
        }
    }

    public void insertSiteGases(List<SiteGas> gases,SiteWork siteWork){
        long siteGasIndex = 0;
        for(SiteGas siteGas:gases) {
            siteGas.setOrderNum(siteGasIndex);
            siteGas.setWorkId(siteWork.getId());
            if(siteWork.getWorkStatus()==1){
                opcTask.addNewItem(siteGas.getItemCode());
            }else if(siteWork.getWorkStatus()==2||siteWork.getWorkStatus()==3){
                opcTask.removeItem(siteGas.getItemCode(),OpcTypeEnum.siteGas);
            }
            siteGasMapper.insertSiteGas(siteGas);
            siteGasIndex++;
        }
    }

    public void insertSiteMonitors(List<SiteMonitor> monitors,SiteWork siteWork){
        long siteMonitorIndex = 0;
        for(SiteMonitor siteMonitor:monitors){
            siteMonitor.setOrderNum(siteMonitorIndex);
            siteMonitor.setWorkId(siteWork.getId());
            if(siteWork.getWorkStatus()==1){
                MonitorDevice monitorDevice = new MonitorDevice();
                BeanUtils.copyProperties(siteMonitor,monitorDevice);
//                ffmpegTask.addTask(monitorDevice);
            }else if(siteWork.getWorkStatus()==2||siteWork.getWorkStatus()==3){
//                ffmpegTask.stopTask(siteMonitor.getIpAdress());
            }
            siteMonitorMapper.insertSiteMonitor(siteMonitor);
            siteMonitorIndex++;
        }
    }

    /**
     * 穿戴设备下拉
     * @return
     */
    @Override
    public List<SiteWorkerVo.WearDevice> getWearDeviceSelectList(WearDevice wearDevice) {
        return wearDeviceMapper.getSelectList(wearDevice);
    }

    /**
     * 气体监测设备下拉
     * @return
     */
    @Override
    public List<SiteGas> getGasDeviceSelectList(GasDevice gasDevice) {
        return gasDeviceMapper.getSelectList(gasDevice);
    }

    /**
     * 监控设备下拉
     * @return
     */
    @Override
    public List<SiteMonitor> getMonitorDeviceSelectList(MonitorDevice monitorDevice) {
        return monitorDeviceMapper.getSelectList(monitorDevice);
    }

    /**
     * 创建workId
     * @return
     */
    public String createWorkId(){
        String newWorkId ="ZY"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss")+"01";
        String oldWorkId = cacheManager.getCache(CacheName.SITEWORKID).get(WORKID,String.class);
        if(StringUtils.isNotBlank(oldWorkId)&&newWorkId.substring(0,newWorkId.length()-2).equals(oldWorkId.substring(0,oldWorkId.length()-2))){
            int num = Integer.parseInt(oldWorkId.substring(oldWorkId.length()-2))+1;
            if(num>9) {
                newWorkId = newWorkId.substring(0,newWorkId.length()-2)+num;
            }else{
                newWorkId = newWorkId.substring(0,newWorkId.length()-2)+"0"+num;
            }
        }
        cacheManager.getCache(CacheName.SITEWORKID).put(WORKID,newWorkId);
        return newWorkId;
    }
}
