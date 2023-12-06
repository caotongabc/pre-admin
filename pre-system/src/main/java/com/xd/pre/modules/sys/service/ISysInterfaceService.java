package com.xd.pre.modules.sys.service;


import com.xd.pre.modules.sys.domain.SysInterface;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-04
 */
public interface ISysInterfaceService extends IService<SysInterface> {
    /**
     * 查询类型信息
     * @return
     */
    List<SysInterface> selectInterfaceArrayList(String type);
}
