package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysOrderDispatch;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工单派单信息表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-04-05
 */
public interface ISysOrderDispatchService extends IService<SysOrderDispatch> {
    /*
     在退单界面获取派单的信息
     */
    SysOrderDispatch getRecallDispatchInfo(@Param("query")SysOrderDispatch sysOrderDispatch);

    SysOrderDispatch selectOrderAcceptDispatchInfo(@Param("query")SysOrderDispatch sysOrderDispatch);
    IPage<SysOrderDispatch> selectDispatchByOrderId(Page page, @Param("query") SysOrderDispatch sysOrderDispatch);

}
