package com.xd.pre.modules.sys.controller;


import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.service.ISysLineService;
import com.xd.pre.modules.sys.service.ISysStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 线路表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/line")
public class SysLineController {
    @Autowired
    private ISysLineService sysLineService;
    @GetMapping
    public R list(Integer stationId) {
        return R.ok(sysLineService.getLineInfo(stationId));
    }
}

