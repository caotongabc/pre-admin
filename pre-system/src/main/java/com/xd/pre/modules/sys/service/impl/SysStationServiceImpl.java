package com.xd.pre.modules.sys.service.impl;

import com.xd.pre.modules.sys.domain.SysStation;
import com.xd.pre.modules.sys.mapper.SysStationMapper;
import com.xd.pre.modules.sys.service.ISysStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 变电站表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Service
public class SysStationServiceImpl extends ServiceImpl<SysStationMapper, SysStation> implements ISysStationService {

    @Override
    public List<SysStation> getStationInfo(Integer deptId) {
        return baseMapper.getStationInfo(deptId);
    }
}
