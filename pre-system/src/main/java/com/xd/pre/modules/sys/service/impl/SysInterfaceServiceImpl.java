package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;


import com.xd.pre.modules.sys.domain.SysInterface;
import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.mapper.SysInterfaceMapper;
import com.xd.pre.modules.sys.service.ISysInterfaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-04
 */
@Service
public class SysInterfaceServiceImpl extends ServiceImpl<SysInterfaceMapper, SysInterface> implements ISysInterfaceService {

    @Override
    public List<SysInterface> selectInterfaceArrayList(String type) {

        return list(Wrappers.<SysInterface>lambdaQuery()
                // 状态为0的
                .eq(SysInterface::getTpye, type)
                // 开始时间小于等于现在的时间
               );
    }
}
