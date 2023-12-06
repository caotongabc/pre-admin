package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 派单信息表 服务类
 * </p>
 *
 * @author caotonf
 * @since 2022-03-22
 */
public interface ISysWorkDispatchService extends IService<SysWorkDispatch> {
    /*
    退单时显示相应的信息
     */
    SysWorkDispatch getRecallDispatchInfoOne(@Param("query") SysWorkDispatch sysWorkDispatch);
    /*
    回复时时显示相应接单的信息
     */
    SysWorkDispatch getRecAcceptDispatchInfoOne(@Param("query") SysWorkDispatch sysWorkDispatch);
    /*
    详情界面获取所有派单信息
     */
    IPage<SysWorkDispatch>  selectDispatchByRequireId(Page page,@Param("query") SysWorkDispatch sysWorkDispatch);
}
