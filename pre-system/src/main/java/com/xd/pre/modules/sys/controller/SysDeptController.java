package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysCourse;
import com.xd.pre.modules.sys.domain.SysDept;
import com.xd.pre.modules.sys.dto.DeptDTO;
import com.xd.pre.modules.sys.service.ISysDeptService;
import com.xd.pre.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Autowired
    private ISysDeptService deptService;

    /**
     * 保存部门信息
     *
     * @param sysDept
     * @return
     */
    @SysOperaLog(descrption = "保存部门信息")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:dept:add')")
    public R save(@RequestBody SysDept sysDept) {
        return R.ok(deptService.save(sysDept));
    }

    /**
     * 获取部门信息
     *
     * @return
     */
    @GetMapping
    // @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptList() {
        return R.ok(deptService.selectDeptList());
    }
    /**
     * 获取部门信息
     *
     * @return
     */
    @GetMapping("/inRegister")
    // @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptList2() {
        return R.ok(deptService.selectDeptList());
    }
    /**
     * 获取部门信息(数组结构）
     *
     * @return
     */
    @GetMapping("/a")
    // @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptArrayList() {
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        System.out.println("查找部门信息数组形式");
        System.out.println("查找部门信息数组形式"); System.out.println("查找部门信息数组形式");
        System.out.println("查找部门信息数组形式");

        return R.ok(deptService.selectDeptArrayList());
    }
    /**
     * 获取下级部门信息
     *
     * @return
     */
    @GetMapping("/childern")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptChildern(Integer deptId) {
        int a=deptId;
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        System.out.println("查找下级部门信息");
        System.out.println("查找下级部门信息");
        return R.ok(deptService.getDeptChildern(deptId));
    }

    /**
     * 获取部门树
     * @return
     */
    @GetMapping("/tree")
    public R getDeptTree() {
        return R.ok(deptService.getDeptTree());
    }


    /**
     * 更新部门信息
     *
     * @return
     */
    @SysOperaLog(descrption = "更新部门信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:dept:update')")
    public R update(@RequestBody DeptDTO deptDto) {
        return R.ok(deptService.updateDeptById(deptDto));
    }

    /**
     * 根据id删除部门信息
     *
     * @return
     */
    @SysOperaLog(descrption = "根据id删除部门信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(deptService.removeById(id));
    }


}

