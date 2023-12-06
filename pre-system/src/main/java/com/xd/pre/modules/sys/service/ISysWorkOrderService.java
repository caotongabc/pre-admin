package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工作单表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-04-04
 */
public interface ISysWorkOrderService extends IService<SysWorkOrder> {
    /*
   获取工作单信息，并进行相应的分页显示
    */
    IPage<SysWorkOrder> getOrderInfoByPage(Page page, SysWorkOrder sysWorkOrder);
    /*
  获取工人的工作单信息，并进行相应的分页显示
  */
    IPage<SysWorkOrder> getWorkerOrderInfoByPage(Page page, SysWorkOrder sysWorkOrder);

    SysWorkOrder getSingleOrderInfo(SysWorkOrder sysWorkOrder);

}
