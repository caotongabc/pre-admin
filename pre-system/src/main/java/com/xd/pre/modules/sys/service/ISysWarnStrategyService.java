package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarnStrategy;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预警策略 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-09
 */
public interface ISysWarnStrategyService extends IService<SysWarnStrategy> {
    /*
    查询相应预警策略信息，并分页显示
    */
    IPage<SysWarnStrategy> getWarnStrategyInfo(Page page, @Param("query") SysWarnStrategy sysWarnStrategy);

    /*
    发布预警策略信息
     */
    Integer updateStates(List<Integer> id);
    /*
    取消发布预警策略信息
     */
    Integer updateStates2(Integer id);
   /*
   根据单位，装备类型等信息获取预警策略里面的单条信息
    */
    SysWarnStrategy getWarnStrategyInfoOne(@Param("query") SysWarnStrategy sysWarnStrategy);
}
