package com.xd.pre.modules.sys.controller;


import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.service.ISysStationService;
import com.xd.pre.modules.sys.service.impl.SysTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 变电站表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/station")
public class SysStationController {
    @Autowired
    private ISysStationService iSysStationService;
    @GetMapping
    public R list(Integer deptId) {
        return R.ok(iSysStationService.getStationInfo(deptId));
    }
}

