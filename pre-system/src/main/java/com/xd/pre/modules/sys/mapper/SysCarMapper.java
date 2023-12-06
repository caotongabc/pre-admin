package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 车辆信息表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-13
 */
public interface SysCarMapper extends BaseMapper<SysCar> {
    /*
    获取相应的车辆信息
    */
    IPage<SysCar> getCarInfo(Page page, @Param("query")  SysCar sysCar, DataScope dataScope);
    /*
   获取车辆详细信息
   */
     SysCar getCarDetailInfo( @Param("query")  SysCar sysCar, DataScope dataScope);
    /*
    获取车辆的试验信息
     */
    IPage <SysCar> getCarWarnTestInfo(Page page, @Param("query") SysCar sysCar, DataScope dataScope);
    /*
   获取车辆的保险信息
    */
    IPage <SysCar> getCarWarnInsuranceInfo(Page page, @Param("query") SysCar sysCar, DataScope dataScope);
    /*
      验证工具id是否存在，返回1存在，返回0 不存在
      新增的时候要求：toolId 不存在
      编辑的时候要求：1.工具id可以存在，但是这条数据的id必须是相应的原来数据的id（编辑页面不修改toolId时的情况）
                     2.
     */
    @Select("SELECT count(*) FROM sys_car WHERE sys_car.car_number=#{carNumber} and sys_car.id !=#{Id}")
    Integer isExistCars(@Param("carNumber") String carNumber ,@Param("Id") Integer Id);

  /*
   根据单位和班组和库房查询车辆
   */
    @Select("SELECT * FROM sys_car w WHERE w.unit_id=#{unitId} and  w.team_id=#{teamId} and  w.warehourse_id=#{warehourseId} and w.car_type =#{carType} and w.car_use_state='空闲中'")
    List<SysCar > findCarInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId,@Param("warehourseId") Integer warehourseId,@Param("carType") String carType);

}
