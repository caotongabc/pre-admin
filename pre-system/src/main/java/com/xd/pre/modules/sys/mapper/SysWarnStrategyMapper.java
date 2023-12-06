package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarnStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 预警策略 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-09
 */
public interface SysWarnStrategyMapper extends BaseMapper<SysWarnStrategy> {
    /*
    查询相应预警策略信息，并分页显示
    */
    IPage<SysWarnStrategy> getWarnStrategyInfo1(Page page, @Param("query") SysWarnStrategy sysWarnStrategy, DataScope dataScope);
    /*
    发布预警策略信息
     */
    @Update(" UPDATE sys_warn_strategy SET state=9 WHERE sys_warn_strategy.id=#{id}")
    Integer updateState(Integer deptId);
    @Update(" UPDATE sys_warn_strategy SET state=0 WHERE sys_warn_strategy.id=#{id}")
    Integer updateState2(Integer deptId);
}
