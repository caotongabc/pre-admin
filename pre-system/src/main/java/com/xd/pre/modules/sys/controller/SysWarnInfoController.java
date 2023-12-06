package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.domain.SysWarnInfo;
import com.xd.pre.modules.sys.service.impl.SysWarnInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 试验信息表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-10
 */

@RestController
@RequestMapping("/warning")
public class SysWarnInfoController {
    @Autowired
    SysWarnInfoServiceImpl sysWarnInfoService;
    /**
     * 新增工具试验信息
     *
     * @param sysWarnInfo 工具
     * @return R
     */
    @SysOperaLog(descrption = "新增工具试验信息")
    @PostMapping("/tool")
    public R save(@RequestBody SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.saveToolWarnInfo(sysWarnInfo));
    }
    /**
     * 新增车辆试验信息
     *
     * @param sysWarnInfo 车辆
     * @return R
     */
    @SysOperaLog(descrption = "新增车辆试验信息")
    @PostMapping("/car/test")
    public R saveCarTestInfo(@RequestBody SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.saveCarWarnTestInfo(sysWarnInfo));
    }
    /**
     * 新增车辆保险信息
     *
     * @param sysWarnInfo 车辆
     * @return R
     */
    @SysOperaLog(descrption = "新增车辆保险信息")
    @PostMapping("/car/insurance")
    public R saveCarInsurancetInfo(@RequestBody SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.saveCarWarnINsuranceInfo(sysWarnInfo));
    }
    @SysOperaLog(descrption = "查看工具试验信息")
    @GetMapping("/toolDetail")
    public R getToolDetailWarnInfo(Page page, SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.getToolWarnDetailInfo(page,sysWarnInfo));
    }
    @SysOperaLog(descrption = "查看车辆试验信息")
    @GetMapping("/carDetail/test")
    public R getCarDetailTestWarnInfo(Page page, SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.getCarWarnTestDetailInfo(page,sysWarnInfo));
    }
    @SysOperaLog(descrption = "查看车辆保险信息")
    @GetMapping("/carDetail/insurance")
    public R getCarDetailInsueanceWarnInfo(Page page, SysWarnInfo sysWarnInfo) {
        return R.ok(sysWarnInfoService.getCarWarnInsuranceDetailInfo(page,sysWarnInfo));
    }
}

