package com.xd.pre.modules.sys.controller;


import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.service.impl.SysTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/team")
public class SysTeamController {
@Autowired
    private SysTeamServiceImpl sysTeamService;
    @GetMapping
    public R list(String teamUnitid) {
        return R.ok(sysTeamService.getTeamByUnitId(teamUnitid));
    }


}

