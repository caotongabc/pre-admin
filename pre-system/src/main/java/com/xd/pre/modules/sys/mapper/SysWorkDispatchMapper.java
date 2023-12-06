package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 派单信息表 Mapper 接口
 * </p>
 *
 * @author caotonf
 * @since 2022-03-22
 */
public interface SysWorkDispatchMapper extends BaseMapper<SysWorkDispatch> {
    // 获取派单的信息（退单的时候）
    SysWorkDispatch selectRecallDispatchInfo(@Param("query") SysWorkDispatch sysWorkDispatch, DataScope dataScope);
    // 获取接单的信息（回复的时候）
    SysWorkDispatch selectAcceptDispatchInfo(@Param("query") SysWorkDispatch sysWorkDispatch, DataScope dataScope);
    // 获取派单信息根据需求id(在详情界面显示)
    IPage<SysWorkDispatch>  selectDispatchByRequireId(Page page,@Param("query") SysWorkDispatch sysWorkDispatch, DataScope dataScope);

}