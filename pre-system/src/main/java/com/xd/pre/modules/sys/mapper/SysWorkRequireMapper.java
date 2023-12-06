package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 需求表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-19
 */
public interface SysWorkRequireMapper extends BaseMapper<SysWorkRequire> {
    /*
       获取需求的信息，并进行分页显示
       */
    IPage<SysWorkRequire> getRequireInfo(Page page, @Param("query")  SysWorkRequire sysWorkRequire, DataScope dataScope);
    /*
         工人获取需求的信息，并进行分页显示
         */
    IPage<SysWorkRequire> getWorkerRequireInfo(Page page, @Param("query")  SysWorkRequire sysWorkRequire, DataScope dataScope);
    /*
     发布需求信息
   */
    @Update(" UPDATE sys_work_require SET require_status=1 WHERE sys_work_require.id=#{id}")
    Integer submitRequire(Integer deptId);
    /*
    撤销发布
     */
    @Update(" UPDATE sys_work_require SET require_status=0 WHERE sys_work_require.id=#{id}")
    Integer recallRequire(Integer deptId);
    /*
    获取需求的详细信息
  */
    SysWorkRequire getSingleRequireInfo(@Param("query")  SysWorkRequire sysWorkRequire, DataScope dataScope);
}
