package com.xd.pre.modules.sys.mapper;

import com.xd.pre.modules.sys.domain.SysLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.domain.SysStation;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 线路表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface SysLineMapper extends BaseMapper<SysLine> {
    @Select("  SELECT * FROM sys_line WHERE sys_line.station_id=#{stationId}")
    List<SysLine> getLineInfo(Integer stationId);
}
