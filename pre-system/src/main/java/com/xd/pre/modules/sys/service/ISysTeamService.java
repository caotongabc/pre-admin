package com.xd.pre.modules.sys.service;


import com.xd.pre.modules.sys.domain.SysTeam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-05
 */
public interface ISysTeamService extends IService<SysTeam> {
    /**
     * 获取正常租户
     *
     * @return
     */
    List<SysTeam> getTeamByUnitId(String unitId);
}
