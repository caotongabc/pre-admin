package com.xd.pre.modules.sys.service.impl;

import com.xd.pre.modules.sys.domain.SysLine;
import com.xd.pre.modules.sys.mapper.SysLineMapper;
import com.xd.pre.modules.sys.service.ISysLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 线路表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Service
public class SysLineServiceImpl extends ServiceImpl<SysLineMapper, SysLine> implements ISysLineService {
    /*
    获取线路信息
     */
    @Override
    public List<SysLine> getLineInfo(Integer stationId) {
        return baseMapper.getLineInfo(stationId);
    }
}
