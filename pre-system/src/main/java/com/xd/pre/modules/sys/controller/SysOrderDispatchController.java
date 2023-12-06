package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysOrderDispatch;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.xd.pre.modules.sys.service.impl.SysOrderDispatchServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 工单派单信息表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/orderDispatch")
public class SysOrderDispatchController {
    @Autowired
    private SysWorkOrderServiceImpl sysWorkOrderService;
    @Autowired
    private SysOrderDispatchServiceImpl sysOrderDispatchService;
    /**
     * 新增工作单派单信息
     *
     * @param sysOrderDispatch 派单
     * @return R
     */
    @SysOperaLog(descrption = "新增工作单派单信息")
    @PostMapping
    public R saveDispatchInfo(@RequestBody SysOrderDispatch sysOrderDispatch) {
        UpdateWrapper<SysWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.set("order_status",2);
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.eq("order_id",sysOrderDispatch.getOrderId());
        Boolean count=sysWorkOrderService.update(wrapper);
        System.out.println(sysOrderDispatch.getOrderId());
        // 未撤单
        sysOrderDispatch.setIsRecall(0);
        // 更新创建时间和修改时间
        sysOrderDispatch.setCreateTime(LocalDateTime.now());
        sysOrderDispatch.setUpdateTime(LocalDateTime.now());
        // 更新派单状态，已派单（未接单）
        sysOrderDispatch.setDispatchState(0);
        return R.ok(sysOrderDispatchService.save(sysOrderDispatch));
    }
    /*
     *工作单接单
     * @param orderId 派单
     * @return R
     */
    @SysOperaLog(descrption = "工作单接单")
    @DeleteMapping("/accept/{orderId}")
    public R acceptDispatch(@PathVariable String orderId ) {
        // 改变需求表的需求状态为已接单
        UpdateWrapper<SysWorkOrder> wrapper1=new UpdateWrapper<>();
        wrapper1.set("order_status",3);
        wrapper1.eq("order_id",orderId);
        sysWorkOrderService.update(wrapper1);
        // 更新相应的派单信息
        UpdateWrapper<SysOrderDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("handle_type",0);
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("order_id",orderId);
        wrapper.set("dispatch_state",1);
        wrapper.eq("is_recall",0);
        return R.ok(sysOrderDispatchService.update(wrapper));

    }
    /**
     * 工人进行工作单退单操作
     *
     *
     * @param sysOrderDispatch 派单
     * @return
     */
    @SysOperaLog(descrption = "工作单退单")
    @PostMapping("/recall")
    public R recallDispatch(@RequestBody SysOrderDispatch sysOrderDispatch) {
        // 更新工作单派单表的信息
        UpdateWrapper<SysOrderDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("handle_type",1);
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.set("dispatch_state",2); //设置派单的状态为撤单
        wrapper.set("is_recall",1);
        wrapper.set("handle_reason",sysOrderDispatch.getHandleReason());
        wrapper.eq("order_id",sysOrderDispatch.getOrderId());
        wrapper.ne("is_recall",1);
        // 改变工作单表的状态
        UpdateWrapper<SysWorkOrder> wrapper1=new UpdateWrapper<>();
        wrapper1.set("order_status",4);
        wrapper1.eq("order_id",sysOrderDispatch.getOrderId());
        sysWorkOrderService.update(wrapper1);

        return R.ok(sysOrderDispatchService.update(wrapper));

    }
    /**
     * 工人获取退单的信息（获取退单界面的信息）
     *
     *
     * @param sysOrderDispatch 派单
     * @return
     */
    @GetMapping("/recallInfo")
    public R getWorkerSysOrderCoursePageTest(SysOrderDispatch sysOrderDispatch) {
        return R.ok(sysOrderDispatchService.getRecallDispatchInfo( sysOrderDispatch));

    }
    /**
     * 获取接单的信息（谁接单了都能信息，接单时间）
     *
     *
     * @param sysOrderDispatch 派单
     * @return
     */
    @GetMapping("/acceptInfo")
    public R getWorkerAcceptInfoInHandlePage(SysOrderDispatch sysOrderDispatch) {
        return R.ok(sysOrderDispatchService.selectOrderAcceptDispatchInfo(sysOrderDispatch));

    }
    /**
     * 详情页面的派单信息
     *
     *
     * @param sysOrderDispatch 派单
     * @return
     */
    @GetMapping("/dispatchInfo")
    public R getDispatchInfoInDetails(Page page, SysOrderDispatch sysOrderDispatch) {
        return R.ok(sysOrderDispatchService.selectDispatchByOrderId( page,sysOrderDispatch));

    }
}

