package com.xd.pre.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysCourse;
import com.xd.pre.modules.sys.service.ISysCourseService;
import com.xd.pre.modules.sys.service.ISysCourseService;
import org.apache.ibatis.javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/course")
public class SysCourseController {

    @Autowired
   private ISysCourseService sysCourseService;
  //  private ISysCourseService sysCourseService;

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param sysCourse 课程对象
     * @return
     */
    @GetMapping("/page")
    public R getSysCoursePage(Page page, SysCourse sysCourse) {
        QueryWrapper<SysCourse> wrapper = new QueryWrapper<>();
        // wrapper.eq("name",sysCourse.getName());
        if(sysCourse.getCourseName()!=null && sysCourse.getCourseName()!=""){
            // wrapper.eq("name","上海某某公司");

            wrapper.like("course_name",sysCourse.getCourseName());
        }



            if(sysCourse.getCourseDept()!=null && sysCourse.getCourseDept()!=""){
                wrapper.like("course_dept",sysCourse.getCourseDept());
            }
            return R.ok(sysCourseService.page(page, wrapper));
        }


    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param sysCourse 课程对象
     * @return
     */
    @GetMapping("/orderpage")
    public R getSysOrderCoursePage(Page page, SysCourse sysCourse) {
        QueryWrapper<SysCourse> wrapper = new QueryWrapper<>();
        // wrapper.eq("name",sysCourse.getName());
        if(sysCourse.getCourseName()==null || sysCourse.getCourseName()==""){
            // wrapper.eq("name","上海某某公司");
            return R.ok(sysCourseService.page(page, wrapper));
        }
        else {
            wrapper.like("course_name",sysCourse.getCourseName()).orderByAsc();
            if(sysCourse.getCourseDept()!=null|| sysCourse.getCourseDept()!=""){
                wrapper.eq("course_dept",sysCourse.getCourseDept()).orderByAsc();
            }
            return R.ok(sysCourseService.page(page, wrapper));
        }

    }

    /**
     * 查询全部有效的租户
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(sysCourseService.getNormalCourse());
    }


    /**
     * 新增租户
     *
     * @param sysCourse 租户
     * @return R
     */
    @SysOperaLog(descrption = "新增租户")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:course:add')")
    public R save(@RequestBody SysCourse sysCourse) {
        return R.ok(sysCourseService.saveCourse(sysCourse));
    }

    /**
     * 修改租户
     *
     * @param sysCourse 租户
     * @return R
     */
    @SysOperaLog(descrption = "修改租户")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:course:update')")
    public R updateById(@RequestBody SysCourse sysCourse) {
        sysCourse.setUpdateTime(LocalDateTime.now());
        return R.ok(sysCourseService.updateById(sysCourse));
    }


    /**
     * 通过id删除租户
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "删除租户")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:course:del')")
    public R removeById(@PathVariable Integer id) {
        return R.ok(sysCourseService.removeById(id));
    }

    /**
     * 设置租户id -- 主要是第三方登录使用 目的需要发送请求 看看后面有没有办法解决
     *
     * @param tenantId
     */
    @PostMapping("/setting/{tenantId}")
    public R setting(@PathVariable Integer tenantId) {
        return R.ok();
    }


}