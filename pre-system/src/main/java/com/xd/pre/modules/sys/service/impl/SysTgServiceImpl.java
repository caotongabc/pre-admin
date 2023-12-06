package com.xd.pre.modules.sys.service.impl;

import com.xd.pre.modules.sys.domain.SysTg;
import com.xd.pre.modules.sys.mapper.SysTgMapper;
import com.xd.pre.modules.sys.service.ISysTgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 台区表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Service
public class SysTgServiceImpl extends ServiceImpl<SysTgMapper, SysTg> implements ISysTgService {

    @Override
    public List<SysTg> getTgInfo(Integer lineId) {
        return baseMapper.getTgInfo(lineId);
    }
}
