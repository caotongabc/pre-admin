package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysOrderDispatch;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.xd.pre.modules.sys.mapper.SysOrderDispatchMapper;
import com.xd.pre.modules.sys.service.ISysOrderDispatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

/**
 * <p>
 * 工单派单信息表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-04-05
 */
@Service
public class SysOrderDispatchServiceImpl extends ServiceImpl<SysOrderDispatchMapper, SysOrderDispatch> implements ISysOrderDispatchService {


    @Override
    public SysOrderDispatch getRecallDispatchInfo(SysOrderDispatch sysOrderDispatch) {
        return baseMapper.selectRecallDispatchInfo(sysOrderDispatch,new DataScope());
    }

    @Override
    public SysOrderDispatch selectOrderAcceptDispatchInfo(SysOrderDispatch sysOrderDispatch) {
        return baseMapper.selectOrderAcceptDispatchInfo(sysOrderDispatch,new DataScope());
    }

    @Override
    public IPage<SysOrderDispatch> selectDispatchByOrderId(Page page, SysOrderDispatch sysOrderDispatch) {
        return baseMapper.selectDispatchByOrderId(page,sysOrderDispatch,new DataScope());
    }
}
