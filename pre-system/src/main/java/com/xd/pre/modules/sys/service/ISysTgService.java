package com.xd.pre.modules.sys.service;

import com.xd.pre.modules.sys.domain.SysLine;
import com.xd.pre.modules.sys.domain.SysTg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 台区表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface ISysTgService extends IService<SysTg> {
    List<SysTg> getTgInfo(Integer lineId);
}
