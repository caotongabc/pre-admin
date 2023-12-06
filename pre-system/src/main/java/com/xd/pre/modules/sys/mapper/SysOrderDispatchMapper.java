package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysOrderDispatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工单派单信息表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-04-05
 */
public interface SysOrderDispatchMapper extends BaseMapper<SysOrderDispatch> {
    /*
      退单的时候，获取相应的派单信息
     */
    SysOrderDispatch selectRecallDispatchInfo (@Param("query") SysOrderDispatch sysOrderDispatch, DataScope dataScope);
    SysOrderDispatch  selectOrderAcceptDispatchInfo(@Param("query") SysOrderDispatch sysOrderDispatch, DataScope dataScope);
    IPage<SysOrderDispatch> selectDispatchByOrderId(Page page,@Param("query") SysOrderDispatch sysOrderDispatch, DataScope dataScope);

}
