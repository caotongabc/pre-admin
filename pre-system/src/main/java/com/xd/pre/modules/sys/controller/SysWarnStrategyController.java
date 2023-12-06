package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysWarnStrategy;
import com.xd.pre.modules.sys.service.impl.SysWarnStrategyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 预警策略 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/warnStrategy")
public class SysWarnStrategyController {
    @Autowired
    SysWarnStrategyServiceImpl sysWarnStrategyService;
    /**
     * 获取预警策略信息并分页显示
     *
     * @return
     */
    @GetMapping("/fullInfo")
    //有关权限的配置先不做
   // @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getWarnStrategyFullInfo(Page page , SysWarnStrategy sysWarnStrategy) {

        QueryWrapper<SysWarnStrategy> wrapper = new QueryWrapper<>();
        System.out.println("查找预警信息并分页显示");
        System.out.println("查找预警信息并分页显示");
        return R.ok(sysWarnStrategyService.getWarnStrategyInfo( page ,sysWarnStrategy));
    }
    /*
    获取下次试验时间所用的函数
     */
    @GetMapping("/oneInfo")
    public  R getOneWarnStrategyInfo(SysWarnStrategy sysWarnStrategy){
        System.out.println("返回所需的单条预警策略信息");
        return R.ok(sysWarnStrategyService.getWarnStrategyInfoOne(sysWarnStrategy));
    }

    /**
     * 修改库房信息
     *
     * @return
     */
    @SysOperaLog(descrption = "修改预警策略信息")
    @PutMapping("/update")
    public R updateById(@RequestBody SysWarnStrategy sysWarnStrategy) {
        sysWarnStrategy.setUpdateTime(LocalDateTime.now());
        return R.ok(sysWarnStrategyService.updateById(sysWarnStrategy));
    }
    /**
     * 保存库房信息
     *
     * @return
     */
    @SysOperaLog(descrption = "保存预警策略信息")
    @PostMapping("/save")
    public R save(@RequestBody SysWarnStrategy sysWarnStrategy) {
        sysWarnStrategy.setState(0);
        sysWarnStrategy.setCreateTime(LocalDateTime.now());
        return R.ok(sysWarnStrategyService.save(sysWarnStrategy));
    }
    @SysOperaLog(descrption = "批量删除预警策略")
    @PostMapping("/batch/del")
    public R removeByIds(@RequestBody List<String> id) {
        QueryWrapper<SysWarnStrategy> wrapper = new QueryWrapper<>();

        return R.ok(sysWarnStrategyService.removeByIds(id));

    }
    @SysOperaLog(descrption = "发布预警策略")
    @PostMapping("/updateState")
    public R updateStates(@RequestBody List<Integer> id) {
        QueryWrapper<SysWarnStrategy> wrapper = new QueryWrapper<>();

        return R.ok(sysWarnStrategyService.updateStates(id));

    }
    @SysOperaLog(descrption = "取消发布预警策略")
    @PostMapping("/updateState2")
    public R updateStates2(@RequestBody Integer id) {
        QueryWrapper<SysWarnStrategy> wrapper = new QueryWrapper<>();

        return R.ok(sysWarnStrategyService.updateStates2(id));

    }
    @SysOperaLog(descrption = "删除预警策略")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        return R.ok(sysWarnStrategyService.removeById(id));
    }
}

