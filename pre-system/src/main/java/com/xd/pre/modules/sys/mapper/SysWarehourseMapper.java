package com.xd.pre.modules.sys.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 库房表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-06
 */
public interface SysWarehourseMapper extends BaseMapper<SysWarehourse> {
    /*
    根据单位和班组查询库房
    在工具管理页面用到了
    */

    @Select("SELECT * FROM sys_warehourse w WHERE w.unit_id=#{unitId} and  w.team_id=#{teamId} and w.id_delete=0")
    List<SysWarehourse > findWareInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId);
    /*
    查询相应库房信息，并分页显示
    */
    IPage<SysWarehourse> getWareFullInfo(Page page, @Param("query") SysWarehourse sysWarehourse, DataScope dataScope);
    /*
    查询单个库房信息，详情页面
     */
     SysWarehourse getSingleWareInfo ( @Param("query") SysWarehourse sysWarehourse, DataScope dataScope);
}
