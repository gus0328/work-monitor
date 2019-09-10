package com.iccm.system.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gxj on 2018/9/29.
 */
public interface SessionMapper {

    /**
     * 删除所有session信息
     */
    public void deleteAllSession();

    /**
     * 根据过期时间查询即将下线的sessionId
     * @param outlinetime
     * @return
     */
    public List<String> querySessionIdsByOutlinetime(@Param("outlinetime") long outlinetime);

    /**
     * 根据sessionId查询sessionId
     * @param sessionId
     * @return
     */
    public String  querySessionIdBySessionId(@Param("sessionId") String sessionId);
}
