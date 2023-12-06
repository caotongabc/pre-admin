package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.xd.pre.modules.sys.domain.SysTeam;
import com.xd.pre.modules.sys.mapper.SysTeamMapper;
import com.xd.pre.modules.sys.service.ISysTeamService;
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
 * @since 2022-03-05
 */
@Service
public class SysTeamServiceImpl extends ServiceImpl<SysTeamMapper, SysTeam> implements ISysTeamService {

    @Override
    public List<SysTeam> getTeamByUnitId(String unitId) {
        int a= Integer.parseInt(unitId);
       return  list(Wrappers.<SysTeam>lambdaQuery()
                // 状态为0的
                .eq(SysTeam::getTeamUnitid , a)
                // 开始时间小于等于现在的时间
                .eq(SysTeam::getIdDelete,0)
                // 结束时间大于等于现在的时间
                );

    }
}
