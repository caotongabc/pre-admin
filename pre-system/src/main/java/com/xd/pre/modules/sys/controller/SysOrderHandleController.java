package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.*;
import com.xd.pre.modules.sys.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 工作单处理表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/orderHandle")
public class SysOrderHandleController {
    @Autowired
    SysOrderDispatchServiceImpl sysOrderDispatchService;
    @Autowired
    SysOrderHandleServiceImpl sysOrderHandleService;
    @Autowired
    SysWorkOrderServiceImpl sysWorkOrderService;
    @Autowired
    SysToolsServiceImpl sysToolsService;
    @Autowired
    SysCarServiceImpl sysCarService;
    @Autowired
    SysWarehourseServiceImpl sysWarehourseService;
    @Autowired
    SysUserServiceImpl sysUserService;
    @Autowired
    SysDeptServiceImpl sysDeptService;
    @Autowired
    SysTeamServiceImpl sysTeamService;
    /**
     * 新增回复信息
     *
     * @param sysOrderHandle 派单
     * @return R
     */
    @SysOperaLog(descrption = "领取装备")
    @PostMapping("/takeEquipment")
    public R takeEquipment(@RequestBody SysOrderHandle sysOrderHandle) {
        // 改变使用工具和车辆的状态
        if(sysOrderHandle.getToolOne() != null){
            String a1[]= sysOrderHandle.getToolOne().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        if(sysOrderHandle.getToolTwo() != null){
            String a1[]= sysOrderHandle.getToolTwo().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        if(sysOrderHandle.getToolThree() != null){
            String a1[]= sysOrderHandle.getToolThree().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        if(sysOrderHandle.getToolFour() != null){
            String a1[]= sysOrderHandle.getToolFour().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        if(sysOrderHandle.getToolFive() != null){
            String a1[]= sysOrderHandle.getToolFive().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        if(sysOrderHandle.getToolSix() != null){
            String a1[]= sysOrderHandle.getToolSix().split(",");
            System.out.println(a1);
            for (int i = 0; i <a1.length ; i++) {
                System.out.println(i);
                System.out.println(a1[i]);
                UpdateWrapper<SysTools> wrapper1= new UpdateWrapper<>();
                wrapper1.set("use_state","使用中");
                wrapper1.eq("tool_id",a1[i]);
                sysToolsService.update(wrapper1);

            }
        }
        // 改变车辆的状态
        if(sysOrderHandle.getCarNumber()!=null){
            UpdateWrapper<SysCar> wrapper1= new UpdateWrapper<>();
            wrapper1.set("car_use_state","使用中");
            wrapper1.eq("car_number",sysOrderHandle.getCarNumber());
            sysCarService.update(wrapper1);
        }


        // 更新工作单的信息
        UpdateWrapper<SysWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.set("order_status",11); //已领取装备
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        Boolean count1=sysWorkOrderService.update(wrapper);

        // 保存领取装备的信息

        //获取相应的派单信息(这里主要是把相应的信息给)，
        QueryWrapper<SysOrderDispatch> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("order_id",sysOrderHandle.getOrderId());
        queryWrapper.eq("dispatch_state",1);
        queryWrapper.eq("is_recall",0);
        SysOrderDispatch ReallsysWorkDispatch= sysOrderDispatchService.getOne(queryWrapper);

        // 更新相应的处理信息，把相应的判单的id,以及相应的接单人，负责人都相应的进行添加到回复信息之中
        sysOrderHandle.setDispatchId(ReallsysWorkDispatch.getId());
        sysOrderHandle.setFeedbackTime(LocalDateTime.now());
        sysOrderHandle.setFeedbackPeopleId(ReallsysWorkDispatch.getManage());
        QueryWrapper<SysUser> queryWrapper2 =new QueryWrapper<>();
        queryWrapper2.eq("user_id",ReallsysWorkDispatch.getManage());
        SysUser sysUser =sysUserService.getOne(queryWrapper2);
        sysOrderHandle.setFeedbackPeopleName(ReallsysWorkDispatch.getManage().toString());
        sysOrderHandle.setManage(sysUser.getUsername());
        System.out.println(sysOrderHandle.getOrderId());
        sysOrderHandle.setCreateTime(LocalDateTime.now());
        sysOrderHandle.setLinquTime(LocalDateTime.now());

        // 回复时更新派单状态未相应的已回复
        UpdateWrapper<SysOrderDispatch> wrapper3 = new UpdateWrapper<>();
        wrapper3.set("dispatch_state",6); //已领取装备
        wrapper3.set("update_time", LocalDateTime.now());
        wrapper3.eq("order_id",sysOrderHandle.getOrderId());
        wrapper3.eq("dispatch_state",1);
        wrapper3.eq("is_recall",0);
        Boolean count=sysOrderDispatchService.update(wrapper3);
        return R.ok(sysOrderHandleService.save(sysOrderHandle));
    }
    /*
     到达现场操作
  */
    @SysOperaLog(descrption = "到达现场")
    @PutMapping("/arrive")
    public R acceptDispatch(@RequestBody SysOrderHandle sysOrderHandle ) {
        // 改变工作单的状态为到达现场
        UpdateWrapper<SysWorkOrder> wrapper1=new UpdateWrapper<>();
        wrapper1.set("order_status",8);// 到达现场
        wrapper1.eq("order_id",sysOrderHandle.getOrderId());
        sysWorkOrderService.update(wrapper1);
        // 更新相应的派单信息（派单状态为到达现场）
        UpdateWrapper<SysOrderDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        wrapper.set("dispatch_state",7); // 到达现场
        wrapper.eq("is_recall",0);
        sysOrderDispatchService.update(wrapper);
        // 更新工作单的处理信息
        UpdateWrapper<SysOrderHandle> wrapper4=new UpdateWrapper<>();
        wrapper4.set("arrive_time",LocalDateTime.now());
        wrapper4.set("live_condition",sysOrderHandle.getLiveCondition());
        wrapper4.eq("order_id",sysOrderHandle.getOrderId());
        return R.ok(sysOrderHandleService.update(wrapper4));

    }
    /*
    开工操作
    */
    @SysOperaLog(descrption = "开工")
    @PutMapping("/startWork")
    public R startWork(@RequestBody SysOrderHandle sysOrderHandle ) {

        // 改变工作单的状态为开工
        UpdateWrapper<SysWorkOrder> wrapper1=new UpdateWrapper<>();
        wrapper1.set("order_status",9);// 开工
        wrapper1.eq("order_id",sysOrderHandle.getOrderId());
        sysWorkOrderService.update(wrapper1);

        // 更新相应的派单信息（派单状态为开工）
        UpdateWrapper<SysOrderDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        wrapper.set("dispatch_state",8); // 开工
        wrapper.eq("is_recall",0);
        sysOrderDispatchService.update(wrapper);

        // 更新工作单的处理信息
        UpdateWrapper<SysOrderHandle> wrapper4=new UpdateWrapper<>();
        wrapper4.set("start_work_time",sysOrderHandle.getStartWorkTime());
        wrapper4.set("work_before_photo",sysOrderHandle.getWorkBeforePhoto());
        wrapper4.set("worker_photo",sysOrderHandle.getWorkerPhoto());
        wrapper4.set("update_time",LocalDateTime.now());
        wrapper4.eq("order_id",sysOrderHandle.getOrderId());
        return R.ok(sysOrderHandleService.update(wrapper4));

    }
    /*
     完工操作处理
   */
    @SysOperaLog(descrption = "完工")
    @PutMapping("/finishWork")
    public R finishWork(@RequestBody SysOrderHandle sysOrderHandle ) {

        // 改变工作单的状态为开工
        UpdateWrapper<SysWorkOrder> wrapper1=new UpdateWrapper<>();
        wrapper1.set("order_status",5);// 待审核
        wrapper1.eq("order_id",sysOrderHandle.getOrderId());
        sysWorkOrderService.update(wrapper1);

        // 更新相应的派单信息（派单状态为 待审核）
        UpdateWrapper<SysOrderDispatch> wrapper=new UpdateWrapper<>();
        wrapper.set("update_time",LocalDateTime.now());
        wrapper.set("handle_time",LocalDateTime.now());
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        wrapper.set("dispatch_state",3); // 待审核
        wrapper.eq("is_recall",0);
        sysOrderDispatchService.update(wrapper);

        // 更新工作单的处理信息
        UpdateWrapper<SysOrderHandle> wrapper4=new UpdateWrapper<>();
        wrapper4.set("finish_work_time",sysOrderHandle.getFinishWorkTime());
        wrapper4.set("work_after_photo",sysOrderHandle.getWorkAfterPhoto());
        wrapper4.set("finish_note",sysOrderHandle.getFinishNote());
        wrapper4.set("update_time",LocalDateTime.now());
        wrapper4.eq("order_id",sysOrderHandle.getOrderId());
        return R.ok(sysOrderHandleService.update(wrapper4));

    }
    /**
     * 修改回复信息 (也就是复勘操作)
     *
     * @param sysOrderHandle 工作单
     * @return R
     */
    @SysOperaLog(descrption = "返工")
    @PutMapping("workAgain")
    public R updateFeedback(@RequestBody SysOrderHandle sysOrderHandle) {
        // 更新需求的信息,把状态设置未待审核
        UpdateWrapper<SysWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.set("order_status",5);
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        Boolean count=sysWorkOrderService.update(wrapper);
        // 保存回复信息

        //获取相应的派单信息，在复勘之后把相应的派单状态变成已回复(待审核)
        QueryWrapper<SysOrderDispatch> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("order_id",sysOrderHandle.getOrderId());
        queryWrapper2.eq("dispatch_state",5);
        queryWrapper2.eq("is_recall",0);
        SysOrderDispatch sysOrderDispatch = new SysOrderDispatch();
        sysOrderDispatch.setOrderId(sysOrderHandle.getOrderId());
        sysOrderDispatch.setDispatchState(5);
        sysOrderDispatch.setIsRecall(0);
        SysOrderDispatch ReallsysWorkDispatch= sysOrderDispatchService.getOne(queryWrapper2);
        ReallsysWorkDispatch.setDispatchState(3);

        Boolean b =sysOrderDispatchService.updateById(ReallsysWorkDispatch);
        System.out.println("修改派单信息是否成功"+b);
        // 更新相应的处理信息（数据来源于传入的参数）
        sysOrderHandle.setUpdateTime(LocalDateTime.now());
        if(("").equals(sysOrderHandle.getWorkAfterPhoto())){
            sysOrderHandle.setWorkAfterPhoto("");
        }

        return R.ok(sysOrderHandleService.updateById(sysOrderHandle));
    }

    /**
     * 获取工作单的处理信息
     *
     *
     * @param sysOrderHandle 派单
     * @return
     */
    @GetMapping("/Info")
    public R getHandleInfoInHandlePage(SysOrderHandle sysOrderHandle) {
        QueryWrapper<SysOrderHandle> wrapper =new QueryWrapper<>();
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        return R.ok(sysOrderHandleService.getOne(wrapper));
    }
    /**
     * 获取工作单车辆信息
     *
     *
     * @param sysOrderHandle 派单
     * @return
     */
    @GetMapping("/orderCarInfo")
    public R getOrderCarInfo(SysOrderHandle sysOrderHandle) {
        QueryWrapper<SysOrderHandle> wrapper =new QueryWrapper<>();
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        SysOrderHandle sysOrderHandle1=sysOrderHandleService.getOne(wrapper);
        if(sysOrderHandle1!=null){
            QueryWrapper<SysCar> carQueryWrapper =new QueryWrapper<>();
            carQueryWrapper.eq("car_number",sysOrderHandle1.getCarNumber());
            SysCar sysCar=sysCarService.getOne(carQueryWrapper);
            QueryWrapper<SysWarehourse> sysWarehourseQueryWrapper =new QueryWrapper<>();
            sysWarehourseQueryWrapper.eq("warehourse_id",sysCar.getWarehourseId());
            SysWarehourse sysWarehourse=sysWarehourseService.getOne(sysWarehourseQueryWrapper);
            sysCar.setCarNote(sysWarehourse.getWarehourseName());
            return R.ok(sysCar);
        }
        else return R.ok(null);

    }
    /**
     * 获取工作单工具信息
     *
     *
     * @param sysOrderHandle 派单
     * @return
     */
    @GetMapping("/orderToolInfo")
    public R getOrderToolInfo(Page page, SysOrderHandle sysOrderHandle) {
        QueryWrapper<SysOrderHandle> wrapper =new QueryWrapper<>();
        wrapper.eq("order_id",sysOrderHandle.getOrderId());
        SysOrderHandle sysOrderHandle1=sysOrderHandleService.getOne(wrapper);
        if(sysOrderHandle1!=null){
            List<String> ids = new ArrayList<>();
            System.out.println(sysOrderHandle1.getToolOne());
            System.out.println(sysOrderHandle1.getToolTwo());
            System.out.println(sysOrderHandle1.getToolThree());
            System.out.println(sysOrderHandle1.getToolFour());
            System.out.println(sysOrderHandle1.getToolFive());
            System.out.println(sysOrderHandle1.getToolSix());
            if(sysOrderHandle1.getToolOne() != null && !sysOrderHandle1.getToolOne().equals("")){
                String a1[]= sysOrderHandle1.getToolOne().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);

                }
            }
            if(sysOrderHandle1.getToolTwo() != null && !sysOrderHandle1.getToolTwo().equals("")){
                String a1[]= sysOrderHandle1.getToolTwo().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);

                }
            }
            if(sysOrderHandle1.getToolThree() != null && !sysOrderHandle1.getToolThree().equals("") ){
                String a1[]= sysOrderHandle1.getToolThree().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);
                }
            }
            if(sysOrderHandle1.getToolFour() != null&& !sysOrderHandle1.getToolFour().equals("")){
                String a1[]= sysOrderHandle1.getToolFour().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);

                }
            }
            if(sysOrderHandle1.getToolFive() != null&& !sysOrderHandle1.getToolFive().equals("")){
                String a1[]= sysOrderHandle1.getToolFive().split(",");
                System.out.println(a1);
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);

                }
            }
            if(sysOrderHandle1.getToolSix() != null && !sysOrderHandle1.getToolSix().equals("")){
                String a1[]= sysOrderHandle1.getToolSix().split(",");
                for (int i = 0; i <a1.length ; i++) {
                    System.out.println(i);
                    System.out.println(a1[i]);
                    ids.add(a1[i]);

                }
            }
            System.out.println(ids);
            QueryWrapper<SysTools> sysToolsQueryWrapper =new QueryWrapper<>();
            sysToolsQueryWrapper.in("tool_id",ids);
            // sysToolsService.page(page ,sysToolsQueryWrapper);
            IPage<SysTools> ListTools =sysToolsService.page(page ,sysToolsQueryWrapper);
            for (int i = 0; i < ListTools.getRecords().size() ; i++) {
                SysTools sysTools=  ListTools.getRecords().get(i);
                QueryWrapper<SysTools>  t =new QueryWrapper<>();
                t.eq("tool_id",sysTools.getToolId());
                SysTools realTool=sysToolsService.getOne(t);
                QueryWrapper<SysDept> sysDeptQueryWrapper =new QueryWrapper<>();
                sysDeptQueryWrapper.eq("dept_id",realTool.getToolUnit());
                sysDeptService.getOne(sysDeptQueryWrapper).getName();
                QueryWrapper<SysTeam> sysTeamQueryWrapper =new QueryWrapper<>();
                sysTeamQueryWrapper.eq("team_id",realTool.getToolTeam());
                sysTeamService.getOne(sysTeamQueryWrapper).getTeamName();
                QueryWrapper<SysWarehourse> sysWarehourseQueryWrapper =new QueryWrapper<>();
                sysWarehourseQueryWrapper.eq("warehourse_id",realTool.getToolWarehourse());
                sysWarehourseService.getOne(sysWarehourseQueryWrapper).getWarehourseName();
                sysTools.setUnitName(sysDeptService.getOne(sysDeptQueryWrapper).getName());;
                sysTools.setTeamName(sysTeamService.getOne(sysTeamQueryWrapper).getTeamName());
                sysTools.setWarehourseName( sysWarehourseService.getOne(sysWarehourseQueryWrapper).getWarehourseName());
                sysTools.setUpdateTime(sysOrderHandle1.getLinquTime());
            }
            ListTools.getRecords().size();
            return R.ok(ListTools);

        }
        else  return R.ok(null);

    }

}

