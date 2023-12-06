package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarnInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 试验信息表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-10
 */
public interface SysWarnInfoMapper extends BaseMapper<SysWarnInfo> {
    /*
     获取工具的试验信息
     */
    IPage<SysWarnInfo> getToolWarnDetailInfo(Page page, @Param("query") SysWarnInfo sysWarnInfo, DataScope dataScope);
    /*
     获取车辆的试验信息
     */
    IPage<SysWarnInfo> getCarWarnTestDetailInfo(Page page, @Param("query") SysWarnInfo sysWarnInfo, DataScope dataScope);
    /*
     获取车辆的保险信息
     */
    IPage<SysWarnInfo> getCarlWarnInsuranceDetailInfo(Page page, @Param("query") SysWarnInfo sysWarnInfo, DataScope dataScope);
}
