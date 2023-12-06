package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.domain.SysCourse;
import com.xd.pre.modules.sys.domain.SysInterface;
import com.xd.pre.modules.sys.service.ISysInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-04
 */
@RestController
@RequestMapping("/sys-interface")
public class SysInterfaceController {
@Autowired
private ISysInterfaceService iSysInterfaceService;

    @GetMapping("/gettype")
    public R list( String type) {
          String a=type;
        System.out.println(a);
        QueryWrapper<SysInterface> wrapper = new QueryWrapper<>();

        return R.ok(iSysInterfaceService.selectInterfaceArrayList(a));
    }
}

