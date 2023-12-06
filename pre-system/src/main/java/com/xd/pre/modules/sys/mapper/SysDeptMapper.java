package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.domain.SysDept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    @Select("SELECT * FROM sys_dept dept WHERE  dept.dept_id=#{deptId} OR dept.parent_id=#{deptId}")
    List<SysDept> findChildernDept(Integer deptId);
}
