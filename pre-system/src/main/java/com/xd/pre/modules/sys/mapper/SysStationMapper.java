package com.xd.pre.modules.sys.mapper;

import com.xd.pre.modules.sys.domain.SysStation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 变电站表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface SysStationMapper extends BaseMapper<SysStation> {
    @Select("SELECT * FROM sys_station WHERE sys_station.dept_id IN(SELECT d.dept_id FROM sys_dept  AS d WHERE d.dept_id=#{deptId} OR d.parent_id=#{deptId} )")
    List<SysStation> getStationInfo(Integer deptId);
}

