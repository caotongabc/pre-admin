package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysDept;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 库房表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-06
 */
public interface ISysWarehourseService extends IService<SysWarehourse> {
    /**
     * 获取本级以及下级单位
     * @return
     */
    List<SysWarehourse> getWareInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId);
    /*
   查询相应库房信息，并分页显示
   */
    IPage<SysWarehourse> getWareFullInfo(Page page, @Param("query") SysWarehourse sysWarehourse);
    /*
    用详情页面显示库房信息
     */
    SysWarehourse getSingleWarehourseInfo (@Param("query") SysWarehourse sysWarehourse);
}
