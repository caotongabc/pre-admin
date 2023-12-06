package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysCar;
import com.xd.pre.modules.sys.domain.SysDept;
import com.xd.pre.modules.sys.domain.SysTools;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-03
 */
public interface SysToolsMapper extends BaseMapper<SysTools> {
   /*
    获取相应的工具信息
    */
    IPage <SysTools> getToolInfo(Page page, @Param("query") SysTools sysTools, DataScope dataScope);
    /*
    获取工具的试验信息
     */
    IPage <SysTools> getToolWarnInfo(Page page, @Param("query") SysTools sysTools, DataScope dataScope);
    /*
      验证工具id是否存在，返回1存在，返回0 不存在
      新增的时候要求：toolId 不存在
      编辑的时候要求：1.工具id可以存在，但是这条数据的id必须是相应的原来数据的id（编辑页面不修改toolId时的情况）
                     2.
     */
    @Select("SELECT count(*) FROM sys_tools WHERE  sys_tools.tool_id=#{toolId} and sys_tools.id !=#{Id}")
    Integer isExistTools(@Param("toolId") Integer toolId ,@Param("Id") Integer Id);
    /*
    获取单个工具信息
     */

    SysTools getSingleToolInfo(@Param("query") SysTools sysTools, DataScope dataScope);


 /*
  根据单位和班组和库房查询工具
  */
 @Select("SELECT * FROM sys_tools w WHERE  w.tool_warehourse=#{warehourseId} and w.big_type =#{toolType} and w.use_state='空闲中'")
 List<SysTools > findToolInfo(@Param("warehourseId") Integer warehourseId,@Param("toolType") String toolType);

}
