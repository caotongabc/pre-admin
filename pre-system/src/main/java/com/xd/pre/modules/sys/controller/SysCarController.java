package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysCar;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.xd.pre.modules.sys.service.ISysCarService;
import com.xd.pre.modules.sys.service.impl.SysCarServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 车辆信息表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/car")
public class SysCarController {
    @Autowired
    private ISysCarService sysCarService ;

    /**
     * 自定义特殊分页查询
     *
     * @param page      分页对象
     * @param sysCar 车辆
     * @return
     */
    @GetMapping("/page")
    public R getSysOrderCoursePageTest(Page page, SysCar sysCar) {
        System.out.println(page);
        System.out.println(sysCar);
        return R.ok(sysCarService.getCarInfo2(page, sysCar));

    }
    /**
     * 查询单个车辆信息
     *
     * @param sysCar 车辆
     * @return
     */
    @GetMapping("/carInfo")
    public R getCarDetalisInfo(SysCar sysCar) {
        return R.ok(sysCarService.getCarDetalsInfo(sysCar));

    }

    /**
     * 自定义特殊分页查询(用于试验信息查询)
     *
     * @param page      分页对象
     * @param sysCar 车辆
     * @return
     */
    @GetMapping("/page5")
    public R getSysOrderWarnCarTestPage(Page page, SysCar sysCar) {
        return R.ok(sysCarService.getCarWarnTestInfo(page, sysCar));

    }
    /**
     * 自定义特殊分页查询(用于保险信息查询)
     *
     * @param page      分页对象
     * @param sysCar 车辆
     * @return
     */
    @GetMapping("/page6")
    public R getSysOrderWarnCarInsurancePage(Page page, SysCar sysCar) {
        return R.ok(sysCarService.getCarWarnInsuranceInfo(page, sysCar));

    }


    /**
     * 新曾车辆
     *
     * @param sysCar 车辆
     * @return R
     */
    @SysOperaLog(descrption = "新增车辆")
    @PostMapping
    public R save(@RequestBody SysCar sysCar) {
        sysCar.setCreateTime(LocalDateTime.now());
        sysCar.setCarUseState("空闲中");
        sysCar.setTestState(1);
        sysCar.setIsDelete(0);
        sysCar.setInsuranceState(1);
        sysCar.setCarState(0);
        return R.ok(sysCarService.save(sysCar));
    }

    /**
     * 修改工具
     *
     * @param sysCar 车辆
     * @return R
     */
    @SysOperaLog(descrption = "修改车辆")
    @PutMapping
    public R updateById(@RequestBody SysCar sysCar) {
        sysCar.setUpdateTime(LocalDateTime.now());
        return R.ok(sysCarService.updateById(sysCar));
    }


    /**
     * 通过id删除车辆
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "删除车辆")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        SysCar sysCar =new SysCar();
        sysCar.setId(id);
        sysCar.setIsDelete(1);
        return R.ok(sysCarService.updateById(sysCar));
    }
    /**
     * 通过id批量删除车辆
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "批量删除车辆")
    @PostMapping("/batch/del")
    public R removeByIds(@RequestBody List<String> id) {
        QueryWrapper<SysCar> wrapper = new QueryWrapper<>();

        return R.ok(sysCarService.removeByIds(id));

    }

    /*
     验证车辆id是否存在
     */
    @GetMapping("/isExist")
    public R getIsExistToolId(@Param("carNumber") String carNumber, @Param("id") Integer id) {

        return R.ok(sysCarService.isExistCarNmber(carNumber,id));
    }
    /**
     * 获取车牌号信息（单位和班组下的）
     *
     * @return
     */
    @GetMapping("/freeCarInfo")
    public R getWareHourseInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId ,@Param("warehourseId") Integer warehourseId,@Param("carType") String carType) {
        int a=unitId;
        int b =teamId;
        QueryWrapper<SysWarehourse> wrapper = new QueryWrapper<>();
        System.out.println("根据单位和班组,库房和车辆类型查找车辆信息");
        System.out.println("根据单位和班组,库房和车辆类型查找车辆信息");
        return R.ok(sysCarService.getCarInfo3( unitId,teamId,warehourseId,carType));
    }
}

