package com.xd.pre.modules.sys.service;

import com.xd.pre.modules.sys.domain.SysLine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 线路表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface ISysLineService extends IService<SysLine> {
    List<SysLine> getLineInfo(Integer stationId);
}
