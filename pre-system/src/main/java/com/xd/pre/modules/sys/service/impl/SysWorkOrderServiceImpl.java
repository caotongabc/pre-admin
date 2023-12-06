package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.xd.pre.modules.sys.mapper.SysWorkOrderMapper;
import com.xd.pre.modules.sys.service.ISysWorkOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作单表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-04-04
 */
@Service
public class SysWorkOrderServiceImpl extends ServiceImpl<SysWorkOrderMapper, SysWorkOrder> implements ISysWorkOrderService {

    @Override
    public IPage<SysWorkOrder> getOrderInfoByPage(Page page, SysWorkOrder sysWorkOrder) {
        return baseMapper.getOrderInfoByPage(page,sysWorkOrder,new DataScope());
    }

    @Override
    public IPage<SysWorkOrder> getWorkerOrderInfoByPage(Page page, SysWorkOrder sysWorkOrder) {
        return baseMapper.getWorkerOrderInfoByPage(page,sysWorkOrder,new DataScope());
    }

    @Override
    public SysWorkOrder getSingleOrderInfo(SysWorkOrder sysWorkOrder) {
        return baseMapper.getSingleOrderInfo(sysWorkOrder,new DataScope());
    }
}
