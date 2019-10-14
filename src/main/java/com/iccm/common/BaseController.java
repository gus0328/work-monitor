package com.iccm.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iccm.common.page.PageDomain;
import com.iccm.common.page.TableDataInfo;
import com.iccm.common.page.TableSupport;
import com.iccm.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller基类
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            if(StringUtils.isBlank(orderBy)){
                PageHelper.startPage(pageNum,pageSize);
            }else{
                PageHelper.startPage(pageNum, pageSize, orderBy);
            }
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }


}
