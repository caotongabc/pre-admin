package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarnStrategy;
import com.xd.pre.modules.sys.mapper.SysWarnStrategyMapper;
import com.xd.pre.modules.sys.service.ISysWarnStrategyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 预警策略 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-09
 */
@Service
public class SysWarnStrategyServiceImpl extends ServiceImpl<SysWarnStrategyMapper, SysWarnStrategy> implements ISysWarnStrategyService {


    /*
    获取预警策略信息，并分页显示
     */
    @Override
    public IPage<SysWarnStrategy> getWarnStrategyInfo(Page page, SysWarnStrategy sysWarnStrategy) {
        return baseMapper.getWarnStrategyInfo1(page,sysWarnStrategy,new DataScope());
    }

    @Override
    public Integer updateStates(List<Integer> ids) {
        Iterator<Integer> iterator = ids.iterator();
        int count=0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            baseMapper.updateState(id);
            count++;
        }
        return count;
    }

    /*
    取消发布策略信息
     */
    @Override
    public Integer updateStates2(Integer id) {
        return  baseMapper.updateState2(id);
    }
    /*
    根据条件获取相应的信息，用与下次试验时间计算的方法
     */

    @Override
    public SysWarnStrategy getWarnStrategyInfoOne(SysWarnStrategy sysWarnStrategy) {
        SysWarnStrategy info = baseMapper.selectOne(Wrappers.<SysWarnStrategy>lambdaQuery()
                .select(SysWarnStrategy::getId,SysWarnStrategy::getCreateTime,SysWarnStrategy::getUnitId,
                        SysWarnStrategy::getType,SysWarnStrategy::getSubType
                ,SysWarnStrategy::getWarnType,SysWarnStrategy::getWarnNum,SysWarnStrategy::getExpirationDate)
                .eq(SysWarnStrategy::getUnitId,sysWarnStrategy.getUnitId())
                .eq(SysWarnStrategy::getType,sysWarnStrategy.getType())
                .eq(SysWarnStrategy::getSubType,sysWarnStrategy.getSubType())
                .eq(SysWarnStrategy::getWarnType,sysWarnStrategy.getWarnType())
                .eq(SysWarnStrategy::getState,9)
        );
        return info;
    }

}
