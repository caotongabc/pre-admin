package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;

import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.xd.pre.modules.sys.service.ISysCourseService;
import com.xd.pre.modules.sys.service.ISysToolsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/tools")
public class SysToolsController {
    @Autowired
    private ISysToolsService sysToolsService;
    //  private ISysCourseService sysCourseService;

    /**
     * 分页查询
     *
     * @param page      分页对象Toolshed
     * @param sysTools 课程对象
     * @return
     */
    @GetMapping("/page2")
    public R getSysToolsPage(Page page, SysTools sysTools) {
        QueryWrapper<SysTools> wrapper = new QueryWrapper<>();
        // wrapper.eq("name",sysCourse.getName());
        if(sysTools.getToolName()!=null && sysTools.getToolName()!=""){
            // wrapper.eq("name","上海某某公司");

            wrapper.like("tool_name",sysTools.getToolName());
        }



        return R.ok(sysToolsService.page(page, wrapper));
    }


    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param sysTools 课程对象
     * @return
     */
    @GetMapping("/orderpage")
    public R getSysOrderCoursePage(Page page, SysTools sysTools) {
        QueryWrapper<SysTools> wrapper = new QueryWrapper<>();
        // wrapper.eq("name",sysCourse.getName());
        if(sysTools.getToolName()==null || sysTools.getToolName()==""){
            // wrapper.eq("name","上海某某公司");
            return R.ok(sysToolsService.page(page, wrapper));
        }
        else {
            wrapper.like("tool_name",sysTools.getToolName()).orderByAsc();
            if(sysTools.getToolUnit()!=null|| sysTools.getToolUnit()!=0){
                wrapper.eq("tool_unit",sysTools.getToolUnit()).orderByAsc();
            }
            return R.ok(sysToolsService.page(page, wrapper));
        }

    }
    /**
     * 自定义特殊分页查询
     *
     * @param page      分页对象
     * @param sysTools 课程对象
     * @return
     */
    @GetMapping("/page")
    public R getSysOrderCoursePageTest(Page page, SysTools sysTools) {
        return R.ok(sysToolsService.getToolInfo2(page, sysTools));

    }
    /**
     * 自定义特殊分页查询
     *

     * @param sysTools 课程对象
     * @return
     */
    @GetMapping("/toolInfoOne")
    public R getSingleToolInfo( SysTools sysTools) {
        return R.ok(sysToolsService.getSingleToolInfo( sysTools));

    }

    /**
     * 自定义特殊分页查询(用于试验信息查询)
     *
     * @param page      分页对象
     * @param sysTools 课程对象
     * @return
     */
    @GetMapping("/page5")
    public R getSysOrderWarnToolsPage(Page page, SysTools sysTools) {
        return R.ok(sysToolsService.getToolWarnInfo(page, sysTools));

    }
    /**
     * 查询全部有效的工具
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(sysToolsService.getNormalTools());
    }


    /**
     * 新增工具
     *
     * @param sysTools 工具
     * @return R
     */
    @SysOperaLog(descrption = "新增工具")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:tools:add')")
    public R save(@RequestBody SysTools sysTools) {
        sysTools.setIsDelete(0);
        return R.ok(sysToolsService.saveTools(sysTools));
    }

    /**
     * 修改工具
     *
     * @param sysTools 工具
     * @return R
     */
    @SysOperaLog(descrption = "修改工具")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:tools:update')")
    public R updateById(@RequestBody SysTools sysTools) {
        sysTools.setUpdateTime(LocalDateTime.now());
        return R.ok(sysToolsService.updateById(sysTools));
    }


    /**
     * 通过id删除工具
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "删除工具")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:tools:del')")
    public R removeById(@PathVariable Integer id) {
        SysTools sysTools =new SysTools();
        sysTools.setIsDelete(1);
        sysTools.setId(id);
        return R.ok(sysToolsService.updateById(sysTools));
    }
    /**
     * 通过id批量删除工具
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "批量删除工具")
    @PostMapping("/batch/del")
    @PreAuthorize("hasAuthority('sys:tools:del')")
    public R removeByIds(@RequestBody  List<String> id) {
        QueryWrapper<SysTools> wrapper = new QueryWrapper<>();

        return R.ok(sysToolsService.removeByIds(id));

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
    /*
     验证工具id是否存在
     */
    @GetMapping("/isExist")
    public R getIsExistToolId(@Param("toolId") Integer toolId, @Param("id") Integer id) {

        return R.ok(sysToolsService.isExistToolId(toolId,id));
    }
    /**
     * 获取工具信息（库房和工具类别）
     *
     * @return
     */
    @GetMapping("/freeToolInfo")
    public R getWareHourseInfo(@Param("warehourseId") Integer warehourseId,@Param("toolType") String toolType) {
        QueryWrapper<SysWarehourse> wrapper = new QueryWrapper<>();
        System.out.println("根据库房和工具类型查找工具信息");
        System.out.println("根据库房和工具类型查找工具信息");
        return R.ok(sysToolsService.findToolInfo(warehourseId,toolType));
    }
}

