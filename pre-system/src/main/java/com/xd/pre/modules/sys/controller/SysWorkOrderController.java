package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.*;
import com.xd.pre.modules.sys.service.impl.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 工作单表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/workOrder")
public class SysWorkOrderController {
    @Autowired
    SysWorkOrderServiceImpl sysWorkOrderService;
    @Autowired
    private SysOrderDispatchServiceImpl sysOrderDispatchService;
    @Autowired
    private SysOrderHandleServiceImpl sysOrderHandleService;
    @Autowired
    SysToolsServiceImpl sysToolsService;
    @Autowired
    SysCarServiceImpl sysCarService;
    @Autowired
    SysUserServiceImpl sysUserService;
    /**
     * 自定义分页查询（带查询条件）
     *
     * @param page      分页对象
     * @param sysWorkOrder 工作单
     * @return
     */
    @GetMapping("/page")
    public R getSysOrderCoursePageTest(Page page, SysWorkOrder sysWorkOrder) {
        return R.ok(sysWorkOrderService.getOrderInfoByPage(page, sysWorkOrder));

    }
    /**
     * 自定义分页查询（带查询条件）
     *
     * @param page      分页对象
     * @param sysWorkOrder 工作单
     * @return
     */
    @SysOperaLog(descrption = "获取工人工作单信息")
    @GetMapping("/worker/page")
    public R getWorkerOrderCoursePageTest(Page page, SysWorkOrder sysWorkOrder) {
        return R.ok(sysWorkOrderService.getWorkerOrderInfoByPage(page, sysWorkOrder));

    }
    /**
     * 审核工作单
     *
     * @param  sysWorkOrder 工作单
     * @return R
     */
    @SysOperaLog(descrption = "审核操作")
    @PutMapping("/audit")
    public R auditRequireById(@RequestBody SysWorkOrder sysWorkOrder) {
        sysWorkOrder.setUpdateTime(LocalDateTime.now());
        sysWorkOrder.setAuditTime(LocalDateTime.now());
        // 根据审核的结果来及更新派单的信息
        if( "通过".equals(sysWorkOrder.getAuditResult())){
            sysWorkOrder.setOrderStatus(6);
            QueryWrapper<SysOrderDispatch> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id",sysWorkOrder.getOrderId());
            wrapper.eq("dispatch_state",3);
            wrapper.eq("is_recall",0);
            // 查询到相应的派单信息之后，把派单的状态改变并进行相应的更新操作
            SysOrderDispatch sysOrderDispatch= sysOrderDispatchService.getOne(wrapper);
            sysOrderDispatch.setDispatchState(4);
            sysOrderDispatch.setUpdateTime(LocalDateTime.now());
            //把相应的车辆以及工器具的状态改变成空闲
            QueryWrapper<SysOrderHandle> sysOrderHandleQueryWrapper =new QueryWrapper<>();
            sysOrderHandleQueryWrapper.eq("order_id",sysWorkOrder.getOrderId());
            SysOrderHandle realSysOrderHandle =sysOrderHandleService.getOne(sysOrderHandleQueryWrapper);
            // 改变使用工具和车辆的状态
            if(realSysOrderHandle.getToolOne() != null){
                String a1[]= realSysOrderHandle.getToolOne().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            if(realSysOrderHandle.getToolTwo() != null){
                String a1[]= realSysOrderHandle.getToolTwo().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            if(realSysOrderHandle.getToolThree() != null){
                String a1[]= realSysOrderHandle.getToolThree().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            if(realSysOrderHandle.getToolFour() != null){
                String a1[]= realSysOrderHandle.getToolFour().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            if(realSysOrderHandle.getToolFive() != null){
                String a1[]= realSysOrderHandle.getToolFive().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            if(realSysOrderHandle.getToolSix() != null){
                String a1[]= realSysOrderHandle.getToolSix().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                    wrapper1.set("use_state","空闲中");
                    wrapper1.eq("tool_id",a1[i]);
                    sysToolsService.update(wrapper1);

                }
            }
            // 改变车辆的状态
            if(realSysOrderHandle.getCarNumber()!=null){
                UpdateWrapper<SysCar> wrapper1= new UpdateWrapper<>();
                wrapper1.set("car_use_state","空闲中");
                wrapper1.eq("car_number",realSysOrderHandle.getCarNumber());
                sysCarService.update(wrapper1);
            }
            // 根据派单主键进行相应的更新，把派单状态变成相应的已审核成功
            sysOrderDispatchService.updateById(sysOrderDispatch);

        }
        else if( "不通过".equals(sysWorkOrder.getAuditResult())){
            sysWorkOrder.setOrderStatus(7); //设置需求状态为不通过
            // 查询相应的派单状态为3，待审核，且未撤单，同时需求标号对应
            QueryWrapper<SysOrderDispatch> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id",sysWorkOrder.getOrderId());
            wrapper.eq("dispatch_state",3);
            wrapper.eq("is_recall",0);
            SysOrderDispatch sysOrderDispatch= sysOrderDispatchService.getOne(wrapper);
            sysOrderDispatch.setDispatchState(5);
            sysOrderDispatch.setUpdateTime(LocalDateTime.now());
            // 根据派单主键进行相应的更新，把派单状态变成相应的已审核不成功
            sysOrderDispatchService.updateById(sysOrderDispatch);
        }
        return R.ok(sysWorkOrderService.updateById(sysWorkOrder));
    }
    /**
     * 根据id进行查询相应的工作单信息
     *
     *
     * @param sysWorkOrder 工作单
     * @return
     */
    @GetMapping("/orderInfoOne")
    public R getRequireInfoById( SysWorkOrder sysWorkOrder) {
        QueryWrapper<SysWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",sysWorkOrder.getOrderId());

        return R.ok(sysWorkOrderService.getSingleOrderInfo(sysWorkOrder));

    }

}

