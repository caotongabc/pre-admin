package com.xd.pre.modules.sys.service;

import com.xd.pre.modules.sys.domain.SysStation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 变电站表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface ISysStationService extends IService<SysStation> {
    List<SysStation> getStationInfo(Integer deptId);
}
