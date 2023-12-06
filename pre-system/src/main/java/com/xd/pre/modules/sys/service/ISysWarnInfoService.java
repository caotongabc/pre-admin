package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysWarnInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 试验信息表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-10
 */
public interface ISysWarnInfoService extends IService<SysWarnInfo> {
    /*
    保存工具试验信息，并修改工具信息表之中的下次时间
     */
    boolean saveToolWarnInfo(SysWarnInfo sysWarnInfo);

    /*
   保存车辆试验信息，并修改车辆信息表之中的下次时间
    */
    boolean saveCarWarnTestInfo(SysWarnInfo sysWarnInfo);
    /*
   保存车辆保险信息，并修改车辆信息表之中的下次时间
    */
    boolean saveCarWarnINsuranceInfo(SysWarnInfo sysWarnInfo);
    /*
    获取工具的试验信息，并分页(详情页面)
     */
    IPage<SysWarnInfo> getToolWarnDetailInfo(Page page, SysWarnInfo sysWarnInfo);
    /*
   获取车辆的试验信息，并分页(详情页面)
    */
    IPage<SysWarnInfo> getCarWarnTestDetailInfo(Page page, SysWarnInfo sysWarnInfo);
    /*
   获取车辆的保险信息，并分页(详情页面)
    */
    IPage<SysWarnInfo> getCarWarnInsuranceDetailInfo(Page page, SysWarnInfo sysWarnInfo);
}
