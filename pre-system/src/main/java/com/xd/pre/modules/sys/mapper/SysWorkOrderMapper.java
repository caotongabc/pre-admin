package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 工作单表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-04-04
 */
public interface SysWorkOrderMapper extends BaseMapper<SysWorkOrder> {
    /*
      获取工单的信息，并进行分页显示
      */
    IPage<SysWorkOrder> getOrderInfoByPage(Page page, @Param("query")  SysWorkOrder sysWorkOrder, DataScope dataScope);
    /*
     获取工人工单的信息，并进行分页显示
     */
    IPage<SysWorkOrder> getWorkerOrderInfoByPage(Page page, @Param("query")  SysWorkOrder sysWorkOrder, DataScope dataScope);
    /*
     获取详情页面的工作单信息
     */
    SysWorkOrder getSingleOrderInfo(@Param("query")  SysWorkOrder sysWorkOrder, DataScope dataScope);

}
