package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.xd.pre.modules.sys.mapper.SysWorkDispatchMapper;
import com.xd.pre.modules.sys.service.ISysWorkDispatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 派单信息表 服务实现类
 * </p>
 *
 * @author caotonf
 * @since 2022-03-22
 */
@Service
public class SysWorkDispatchServiceImpl extends ServiceImpl<SysWorkDispatchMapper, SysWorkDispatch> implements ISysWorkDispatchService {

    @Override
    public SysWorkDispatch getRecallDispatchInfoOne(SysWorkDispatch sysWorkDispatch) {
        return baseMapper.selectRecallDispatchInfo(sysWorkDispatch ,new DataScope());
    }

    @Override
    public SysWorkDispatch getRecAcceptDispatchInfoOne(SysWorkDispatch sysWorkDispatch) {
        return baseMapper.selectAcceptDispatchInfo(sysWorkDispatch ,new DataScope());
    }

    @Override
    public IPage<SysWorkDispatch> selectDispatchByRequireId(Page page, SysWorkDispatch sysWorkDispatch) {
        return baseMapper.selectDispatchByRequireId(page,sysWorkDispatch,new DataScope());
    }


}
