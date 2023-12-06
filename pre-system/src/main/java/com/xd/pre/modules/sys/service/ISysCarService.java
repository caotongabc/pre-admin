package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysCar;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车辆信息表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-13
 */
public interface ISysCarService extends IService<SysCar> {
    IPage<SysCar> getCarInfo2(Page page, SysCar sysCar);
    /*

     查询要试验的车辆信息
     */
    IPage<SysCar> getCarWarnTestInfo(Page page, SysCar sysCar);
    /*
     查询要保险的车辆信息
     */
    IPage<SysCar> getCarWarnInsuranceInfo(Page page, SysCar sysCar);
    /*
    检验工具号是否存在
     */
    Integer isExistCarNmber(@Param("carNumber") String carNumber , @Param("id") Integer id);

    /*
     查询车辆的基本信息（单个）
     */
    SysCar getCarDetalsInfo (SysCar sysCar);
    /*
     查询库房下的车辆
     */
    List<SysCar> getCarInfo3(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId,@Param("warehourseId") Integer warehourseId,@Param("carType") String carType);
}
