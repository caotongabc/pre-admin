package com.xd.pre.modules.sys.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;

import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.xd.pre.modules.sys.service.impl.SysWarehourseServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 库房表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/warehourse")
public class SysWarehourseController {
    @Autowired
    SysWarehourseServiceImpl sysWarehourseService;
    /**
     * 获取库房信息（单位和班组下的）
     *
     * @return
     */
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getWareHourseInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId) {
        int a=unitId;
        int b =teamId;
        QueryWrapper<SysWarehourse> wrapper = new QueryWrapper<>();
        System.out.println("根据单位和班组查找库房信息");
        System.out.println("根据单位和班组查找库房信息");
        return R.ok(sysWarehourseService.getWareInfo( unitId,teamId));
    }
    /**
     * 获取库房信息并分页显示
     *
     * @return
     */
    @GetMapping("/fullInfo")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getWareHourseFullInfo(Page page , SysWarehourse sysWarehourse) {

        QueryWrapper<SysWarehourse> wrapper = new QueryWrapper<>();
        System.out.println("查找库房信息并分页显示");
        System.out.println("查找库房信息并分页显示");
        return R.ok(sysWarehourseService.getWareFullInfo( page ,sysWarehourse));
    }
    /**
     * 获取库房信息并分页显示
     *
     * @return
     */
    @GetMapping("/singeleWareHourseInfo")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getSingleWareHourseInfo(SysWarehourse sysWarehourse) {

        return R.ok(sysWarehourseService.getSingleWarehourseInfo( sysWarehourse));
    }
    /**
     * 修改库房信息
     *
     * @return
     */
    @SysOperaLog(descrption = "修改库房")
    @PutMapping("/update")
    public R updateById(@RequestBody SysWarehourse sysWarehourse) {
        sysWarehourse.setUpdateTime(LocalDateTime.now());
        return R.ok(sysWarehourseService.updateById(sysWarehourse));
    }
    /**
     * 保存库房信息
     *
     * @return
     */
    @SysOperaLog(descrption = "保存库房信息")
    @PostMapping("/save")
    public R save(@RequestBody SysWarehourse sysWarehourse) {
        sysWarehourse.setIdDelete(0);
        return R.ok(sysWarehourseService.save(sysWarehourse));
    }
    @SysOperaLog(descrption = "批量删除库房")
    @PostMapping("/batch/del")
    public R removeByIds(@RequestBody List<String> id) {
        QueryWrapper<SysWarehourse> wrapper = new QueryWrapper<>();

        return R.ok(sysWarehourseService.removeByIds(id));

    }
    @SysOperaLog(descrption = "删除库房")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        SysWarehourse sysWarehourse = new SysWarehourse();
        sysWarehourse.setIdDelete(1);
        sysWarehourse.setWarehourseId(id);
        return R.ok(sysWarehourseService.updateById(sysWarehourse));
    }

}

