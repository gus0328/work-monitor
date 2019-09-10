package com.iccm.system.service.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.iccm.common.Constants;
import com.iccm.common.DateUtils;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.utils.JSONUtil;
import com.iccm.system.model.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.SysAuthoritiesMapper;
import com.iccm.system.model.SysAuthorities;
import com.iccm.system.service.ISysAuthoritiesService;
import com.iccm.common.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * 权限标识Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-08
 */
@Service
public class SysAuthoritiesServiceImpl implements ISysAuthoritiesService 
{
    @Autowired
    private SysAuthoritiesMapper sysAuthoritiesMapper;

    @Autowired
    private WebApplicationContext applicationContext;

    /**
     * 查询权限标识
     * 
     * @param authority 权限标识ID
     * @return 权限标识
     */
    @Override
    public SysAuthorities selectSysAuthoritiesById(String authority)
    {
        return sysAuthoritiesMapper.selectSysAuthoritiesById(authority);
    }

    /**
     * 查询权限标识列表
     * 
     * @param sysAuthorities 权限标识
     * @return 权限标识
     */
    @Transactional
    @Override
    public JSONArray selectSysAuthoritiesList(SysAuthorities sysAuthorities)
    {
        List<SysAuthorities> list = sysAuthoritiesMapper.selectSysAuthoritiesList(sysAuthorities);
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss"));
        return JSONUtil.listToTree(jsonArray,"authority","parentUrl","children");
    }

    /**
     * 新增权限标识
     * 
     * @param sysAuthorities 权限标识
     * @return 结果
     */
    @Override
    public int insertSysAuthorities(SysAuthorities sysAuthorities)
    {
        sysAuthorities.setCreateTime(DateUtils.getNowDate());
        return sysAuthoritiesMapper.insertSysAuthorities(sysAuthorities);
    }

    /**
     * 修改权限标识
     * 
     * @param sysAuthorities 权限标识
     * @return 结果
     */
    @Override
    public int updateSysAuthorities(SysAuthorities sysAuthorities)
    {
        sysAuthorities.setUpdateTime(DateUtils.getNowDate());
        return sysAuthoritiesMapper.updateSysAuthorities(sysAuthorities);
    }

    /**
     * 删除权限标识对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysAuthoritiesByIds(String ids)
    {
        return sysAuthoritiesMapper.deleteSysAuthoritiesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除权限标识信息
     * 
     * @param authority 权限标识ID
     * @return 结果
     */
    public int deleteSysAuthoritiesById(String authority)
    {
        return sysAuthoritiesMapper.deleteSysAuthoritiesById(authority);
    }

    /**
     * 删除全部权限标识
     * @return
     */
    @Override
    public int deleteAll() {
        return sysAuthoritiesMapper.deleteAll();
    }

    @Transactional
    @Override
    public JsonResult sync() {
        Map<String,Object> beans = applicationContext.getBeansWithAnnotation(PermissionsApi.class);
        deleteAll();
        long index = 0;
        for(Object bean : beans.values()){
            PermissionsApi permissionsApi = bean.getClass().getAnnotation(PermissionsApi.class);
            SysAuthorities sysAuthorities = new SysAuthorities(permissionsApi.authorities(),permissionsApi.value(), Constants.SYS_AUTH_API,index,Constants.SYS_AUTH_API);
            insertSysAuthorities(sysAuthorities);
            Method[] methods = bean.getClass().getDeclaredMethods();
            long methodIndex = 0;
            for (Method declaredMethod : methods) {
                RequiresPermissions requiresPermissions = AnnotationUtils.findAnnotation(declaredMethod, RequiresPermissions.class);
                if (requiresPermissions != null) {
                    SysAuthorities sysAuthorities1 = new SysAuthorities(requiresPermissions.authorities(), requiresPermissions.value(), sysAuthorities.getAuthorityName(), methodIndex, sysAuthorities.getAuthority());
                    insertSysAuthorities(sysAuthorities1);
                    methodIndex++;
                }
            }
            index++;
        }
        return JsonResult.ok();
    }
}
