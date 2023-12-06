package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.xd.pre.modules.sys.service.impl.SysWorkDispatchServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkRequireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 派单信息表 前端控制器
 * </p>
 *
 * @author caotonf
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/dispatch")
public class SysWorkDispatchController {
    @Autowired
    private SysWorkDispatchServiceImpl sysWorkDispatchService;
    @Autowired
     private SysWorkRequireServiceImpl sysWorkRequireService;
    /**
     * 新增需求派单信息
     *
     * @param sysWorkDispatch 派单
     * @return R
     */
    @SysOperaLog(descrption = "新增需求勘探信息")
    @PostMapping
    public R save(@RequestBody SysWorkDispatch sysWorkDispatch) {
        UpdateWrapper<SysWorkRequire> wrapper = new UpdateWrapper<>();
        wrapper.set("require_status",2);
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.eq("require_id",sysWorkDispatch.getRequireId());
         Boolean count=sysWorkRequireService.update(wrapper);
        System.out.println(sysWorkDispatch.getRequireId());
        sysWorkDispatch.setIsRecall(0);
        sysWorkDispatch.setCreateTime(LocalDateTime.now());
        sysWorkDispatch.setUpdateTime(LocalDateTime.now());
        sysWorkDispatch.setDispatchState(0);
        return R.ok(sysWorkDispatchService.save(sysWorkDispatch));
    }
    /**
     * 通过id接单
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "删除需求")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        UpdateWrapper<SysWorkRequire> wrapper=new UpdateWrapper<>();
        wrapper.set("is_delete",1);
        wrapper.eq("id",id);
        return R.ok(sysWorkRequireService.update(wrapper));
    }
    /*
      需求接单操作
     */
    @SysOperaLog(descrption = "需求接单")
    @DeleteMapping("/accept/{requireId}")
    public R acceptDispatch(@PathVariable String requireId ) {
        // 改变需求表的需求状态
        UpdateWrapper<SysWorkRequire> wrapper1=new UpdateWrapper<>();
        wrapper1.set("require_status",3);
        wrapper1.eq("require_id",requireId);
        sysWorkRequireService.update(wrapper1);
        // 更新相应的派单信息
        UpdateWrapper<SysWorkDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("handle_type",0);
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("require_id",requireId);
        wrapper.set("dispatch_state",1);
        wrapper.eq("is_recall",0);
        return R.ok(sysWorkDispatchService.update(wrapper));

    }
    @SysOperaLog(descrption = "需求界面复勘")
    @DeleteMapping("/watachAgain/{requireId}")
    public R watchAgainDispatch(@PathVariable String requireId ) {
        // 改变需求表的需求状态
        UpdateWrapper<SysWorkRequire> wrapper1=new UpdateWrapper<>();
        wrapper1.set("require_status",3);
        wrapper1.eq("require_id",requireId);
        sysWorkRequireService.update(wrapper1);
        // 更新相应的派单信息
        UpdateWrapper<SysWorkDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("handle_type",0);
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("require_id",requireId);
        wrapper.set("dispatch_state",1);
        wrapper.eq("is_recall",0);
        return R.ok(sysWorkDispatchService.update(wrapper));

    }
    /**
     * 工人退单的操作
     *
     *
     * @param sysWorkDispatch 派单
     * @return
     */
    @SysOperaLog(descrption = "需求退单")
    @PostMapping("/recall")
    public R recallDispatch(@RequestBody SysWorkDispatch sysWorkDispatch) {
        // 更新派单表的信息
        UpdateWrapper<SysWorkDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("handle_type",1);
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.set("dispatch_state",2); //设置派单的状态为撤单
        wrapper.set("is_recall",1);
        wrapper.set("handle_reason",sysWorkDispatch.getHandleReason());
        wrapper.eq("require_id",sysWorkDispatch.getRequireId());
        wrapper.ne("is_recall",1);
        // 改变需求表的状态
        UpdateWrapper<SysWorkRequire> wrapper1=new UpdateWrapper<>();
        wrapper1.set("require_status",4);
        wrapper1.eq("require_id",sysWorkDispatch.getRequireId());
        sysWorkRequireService.update(wrapper1);

        return R.ok(sysWorkDispatchService.update(wrapper));

    }
    /**
     * 工人获取退单的信息
     *
     *
     * @param sysWorkDispatch 派单
     * @return
     */
    @GetMapping("/recallInfo")
    public R getWorkerSysOrderCoursePageTest(SysWorkDispatch sysWorkDispatch) {
        return R.ok(sysWorkDispatchService.getRecallDispatchInfoOne( sysWorkDispatch));

    }
    /**
     * 回复时工人获取接单的信息
     *
     *
     * @param sysWorkDispatch 派单
     * @return
     */
    @GetMapping("/acceptInfo")
    public R getWorkerAcceptInfoInHandlePage(SysWorkDispatch sysWorkDispatch) {
        return R.ok(sysWorkDispatchService.getRecAcceptDispatchInfoOne( sysWorkDispatch));

    }
    /**
     * 详情页面的派单信息
     *
     *
     * @param sysWorkDispatch 派单
     * @return
     */
    @GetMapping("/dispatchInfo")
    public R getDispatchInfoInDetails( Page page,SysWorkDispatch sysWorkDispatch) {
        return R.ok(sysWorkDispatchService.selectDispatchByRequireId( page,sysWorkDispatch));

    }
}

