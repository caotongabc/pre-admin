package com.xd.pre.modules.sys.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.domain.*;
import com.xd.pre.modules.sys.service.ISysWorkRequireService;
import com.xd.pre.modules.sys.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/eCharts")
public class EChartsController {
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
    SysWarnInfoServiceImpl sysWarnInfoService;
    @Autowired
    SysTeamServiceImpl sysTeamService;
    @Autowired
    SysInterfaceServiceImpl sysInterfaceService;
    @Autowired
    private SysWorkRequireServiceImpl sysWorkRequireService;
    @Autowired
    private SysWorkDispatchServiceImpl sysWorkDispatchService;
    @Autowired
    private SysRequireHandleServiceImpl sysRequireHandleService;
    /**
     * 获取接单信息（用于4个数字的处理）
     *
     *
     * @param sysUser 派单
     * @return
     */
    @GetMapping("/acceptWorkInfo")
    public R getAcceptWorkInfo(SysUser sysUser) {
        SysUser realUser=sysUserService.getById(sysUser);
       // 获取年接受工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper.ne("dispatch_state",2);
        sysOrderDispatchQueryWrapper.ne("dispatch_state",0);
        int orderAcceptCount = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper);
        // 获取年退工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper3 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper3.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper3.eq("dispatch_state",2);
        sysOrderDispatchQueryWrapper3.eq("is_recall",1);
        int orderRecallCount = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper3);
        // 获取月接受工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper2 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper2.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper2.ne("dispatch_state",2);
        sysOrderDispatchQueryWrapper2.ne("dispatch_state",0);
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstday);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        sysOrderDispatchQueryWrapper2.le("create_time",lastDay);
        sysOrderDispatchQueryWrapper2.ge("create_time",firstday);

        int orderAcceptCountByMonth = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper2);
        // 获取月退单工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper4 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper4.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper4.eq("dispatch_state",2);
        sysOrderDispatchQueryWrapper4.eq("is_recall",1);
        sysOrderDispatchQueryWrapper4.le("create_time",lastDay);
        sysOrderDispatchQueryWrapper4.ge("create_time",firstday);

        int orderRecallCountByMonth = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper4);

        // 获取年接受勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper.ne("dispatch_state",2);
        sysWorkDispatchQueryWrapper.ne("dispatch_state",0);
        int requireAcceptCount = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper);
        // 获取年退单勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper3 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper3.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper3.eq("dispatch_state",2);
        sysWorkDispatchQueryWrapper3.eq("is_recall",1);
        int requireRecallCount = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper3);
        // 获取月接受勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper2 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper2.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper2.ne("dispatch_state",2);
        sysWorkDispatchQueryWrapper2.ne("dispatch_state",0);
        sysOrderDispatchQueryWrapper2.le("create_time",lastDay);
        sysWorkDispatchQueryWrapper2.ge("create_time",firstday);
        int requireAcceptCountByMonth = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper2);
        // 获取月退单勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper4 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper4.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper4.eq("dispatch_state",2);
        sysWorkDispatchQueryWrapper4.eq("is_recall",1);
        sysOrderDispatchQueryWrapper4.le("create_time",lastDay);
        sysWorkDispatchQueryWrapper4.ge("create_time",firstday);
        int requireRecallCountByMonth = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper4);




        int totalAcceptCount =orderAcceptCount+requireAcceptCount;
        int totalAcceptCountByMonth =requireAcceptCountByMonth+orderAcceptCountByMonth;
        int totalRecallCount = orderRecallCount+requireRecallCount;
        int totalRecallCountByMonth = orderRecallCountByMonth+requireRecallCountByMonth;
        List<Integer> list =new ArrayList<>();
        list.add(totalAcceptCount);
        list.add(totalAcceptCountByMonth);
        list.add(totalRecallCount);
        list.add(totalRecallCountByMonth);
        System.out.println(list);

        return R.ok(list);
    }
    /**
     * 获取作业管理人员的工作信息（用于4个数字的处理）
     *
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/workManageInfo")
    public R getWorkManageInfo(SysUser sysUser) {
        SysUser realUser=sysUserService.getById(sysUser);
        // 获取年新增需求数
        QueryWrapper<SysWorkRequire> sysWorkRequireQueryWrapper =new QueryWrapper<>();
        sysWorkRequireQueryWrapper.eq("apply_people_id",realUser.getUserId());
        int requireCountByYear = sysWorkRequireService.count(sysWorkRequireQueryWrapper);
        // 获取年完成工单数
        QueryWrapper<SysWorkOrder> sysWorkOrderQueryWrapper =new QueryWrapper<>();
        sysWorkOrderQueryWrapper.eq("apply_people_id",realUser.getUserId());
        sysWorkOrderQueryWrapper.eq("order_status",6);
        int orderCountByYear = sysWorkOrderService.count(sysWorkOrderQueryWrapper);
        // 获取月新增需求数
        QueryWrapper<SysWorkRequire> sysWorkRequireQueryWrapper2 =new QueryWrapper<>();
        sysWorkRequireQueryWrapper2.eq("apply_people_id",realUser.getUserId());
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstday);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        sysWorkRequireQueryWrapper2.le("create_time",lastDay);
        sysWorkRequireQueryWrapper2.ge("create_time",firstday);
        int requireCountByMonth = sysWorkRequireService.count(sysWorkRequireQueryWrapper2);
        // 获取月完成工单数
        QueryWrapper<SysWorkOrder> sysWorkOrderQueryWrapper2 =new QueryWrapper<>();
        sysWorkOrderQueryWrapper2.eq("apply_people_id",realUser.getUserId());
        sysWorkOrderQueryWrapper2.eq("order_status",6);
        sysWorkOrderQueryWrapper2.le("create_time",lastDay);
        sysWorkOrderQueryWrapper2.ge("create_time",firstday);
        int orderCountByMonth = sysWorkOrderService.count(sysWorkOrderQueryWrapper2);
        List<Integer> list =new ArrayList<>();
        list.add(requireCountByYear);
        list.add(orderCountByYear);
        list.add(requireCountByMonth);
        list.add(orderCountByMonth);
        return R.ok(list);
    }

    /**
     * 获取仓库管理人员的工作信息（用于4个数字的处理）
     *
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/equipmentWarnInfo")
    public R getEquipmentWarnInfo(SysUser sysUser) {
        SysUser realUser=sysUserService.getById(sysUser);
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstday);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        LocalDateTime firstDayOfYear = date.with(TemporalAdjusters.firstDayOfYear());
        LocalDateTime lastDayOfYear = date.with(TemporalAdjusters.lastDayOfYear());
        // 获取年处理预警数
        QueryWrapper<SysWarnInfo> sysWarnInfoQueryWrapper =new QueryWrapper<>();
        sysWarnInfoQueryWrapper.eq("handle_id",realUser.getUserId());
        sysWarnInfoQueryWrapper.le("create_time",lastDayOfYear);
        sysWarnInfoQueryWrapper.ge("create_time",firstDayOfYear);
        int warnCountByYear = sysWarnInfoService.count(sysWarnInfoQueryWrapper);

        // 获取月处理数
        QueryWrapper<SysWarnInfo> sysWarnInfoQueryWrapper2 =new QueryWrapper<>();
        sysWarnInfoQueryWrapper2.eq("handle_id",realUser.getUserId());
        sysWarnInfoQueryWrapper2.le("create_time",lastDay);
        sysWarnInfoQueryWrapper2.ge("create_time",firstday);
        int warnCountByMonth = sysWarnInfoService.count(sysWarnInfoQueryWrapper2);

        //工具待处理数
        Page page =new Page<SysTools>();
        SysTools sysTools =new SysTools();
        sysTools.setUserUnit(realUser.getDeptId());
        IPage<SysTools> toolWarnInfos= sysToolsService.getToolWarnInfo(page,sysTools);
         int toolWarnCount1 = toolWarnInfos.getRecords().size();
        int toolWarnCount =toolWarnCount1;
        // 车辆待处理数
        SysCar sysCar =new SysCar();
        sysCar.setUserUnit(realUser.getDeptId());
        IPage<SysCar> carIPage= sysCarService.getCarWarnInsuranceInfo(page,sysCar);
        int carWarnCount1 = carIPage.getRecords().size();
        IPage<SysCar> carIPage2= sysCarService.getCarWarnTestInfo(page,sysCar);
        int carWarnCount2 = carIPage2.getRecords().size();
        int carWarnCount =carWarnCount1+carWarnCount2;

        List<Integer> list =new ArrayList<>();
        list.add(warnCountByYear);
        list.add(warnCountByMonth);
        list.add(toolWarnCount);
        list.add(carWarnCount);
        System.out.println(list);

        return R.ok(list);
    }
    /**
     * 获取接单信息用于主页的图表展示
     *
     *
     * @param sysUser 派单
     * @return
     */
    @GetMapping("/acceptOrRecallInfo")
    public R getAcceptOrRecallInfoForChart(SysUser sysUser) {
        int jiedanorder1=0;
        int jiedanorder2=0;
        int jiedanorder3=0;
        int jiedanorder4=0;
        int tuidanorder1=0,tuidanorder2=0,tuidanorder3=0,tuidanorder4=0;
        int tuidanrequire1=0,tuidanrequire2=0,tuidanrequire3=0,tuidanrequire4=0;
        int jiedanrequire1=0,jiedanrequire2=0,jiedanrequire3=0,jiedanrequire4=0;
        SysUser realUser=sysUserService.getById(sysUser);
        // 获取年接受工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper.ne("dispatch_state",2);
        sysOrderDispatchQueryWrapper.ne("dispatch_state",0);
        int orderAcceptCount = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper);
        // 获取季度接工作单数
        List<SysOrderDispatch> orderDispatchList = sysOrderDispatchService.list(sysOrderDispatchQueryWrapper);
        for (SysOrderDispatch od:orderDispatchList) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: jiedanorder1+=1; break;
                case Q2: jiedanorder2+=1;break;
                case Q3: jiedanorder3+=1;break;
                case Q4: jiedanorder4+=1;break;
                default: break;
            }
        }

        // 获取年退工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper3 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper3.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper3.eq("dispatch_state",2);
        sysOrderDispatchQueryWrapper3.eq("is_recall",1);
        int orderRecallCount = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper3);

        List<SysOrderDispatch> orderDispatchList2 = sysOrderDispatchService.list(sysOrderDispatchQueryWrapper3);
        for (SysOrderDispatch od:orderDispatchList2) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: tuidanorder1+=1; break;
                case Q2: tuidanorder2+=1;break;
                case Q3: tuidanorder3+=1;break;
                case Q4: tuidanorder4+=1;break;
                default: break;
            }
        }
        // 获取月接受工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper2 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper2.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper2.ne("dispatch_state",2);
        sysOrderDispatchQueryWrapper2.ne("dispatch_state",0);
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstday);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        sysOrderDispatchQueryWrapper2.le("create_time",lastDay);
        sysOrderDispatchQueryWrapper2.ge("create_time",firstday);

        int orderAcceptCountByMonth = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper2);
        // 获取月退单工作单数
        QueryWrapper<SysOrderDispatch> sysOrderDispatchQueryWrapper4 =new QueryWrapper<>();
        sysOrderDispatchQueryWrapper4.eq("manage",realUser.getUserId());
        sysOrderDispatchQueryWrapper4.eq("dispatch_state",2);
        sysOrderDispatchQueryWrapper4.eq("is_recall",1);
        sysOrderDispatchQueryWrapper4.le("create_time",lastDay);
        sysOrderDispatchQueryWrapper4.ge("create_time",firstday);

        int orderRecallCountByMonth = sysOrderDispatchService.count(sysOrderDispatchQueryWrapper4);

        // 获取年接受勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper.ne("dispatch_state",2);
        sysWorkDispatchQueryWrapper.ne("dispatch_state",0);
        int requireAcceptCount = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper);
        // 获取季度接受勘探数
        List<SysWorkDispatch> requireDispatchList = sysWorkDispatchService.list(sysWorkDispatchQueryWrapper);
        for (SysWorkDispatch od:requireDispatchList) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: jiedanrequire1+=1; break;
                case Q2: jiedanrequire2+=1;break;
                case Q3: jiedanrequire3+=1;break;
                case Q4: jiedanrequire4+=1;break;
                default: break;
            }
        }
        // 获取年退单勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper3 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper3.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper3.eq("dispatch_state",2);
        sysWorkDispatchQueryWrapper3.eq("is_recall",1);
        int requireRecallCount = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper3);
        // 获取季度退单勘探数
        List<SysWorkDispatch> requireDispatchList2 = sysWorkDispatchService.list(sysWorkDispatchQueryWrapper3);
        for (SysWorkDispatch od:requireDispatchList2) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: tuidanrequire1+=1; break;
                case Q2: tuidanrequire2+=1;break;
                case Q3: tuidanrequire3+=1;break;
                case Q4: tuidanrequire4+=1;break;
                default: break;
            }
        }
        // 获取月接受勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper2 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper2.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper2.ne("dispatch_state",2);
        sysWorkDispatchQueryWrapper2.ne("dispatch_state",0);
        sysOrderDispatchQueryWrapper2.le("create_time",lastDay);
        sysWorkDispatchQueryWrapper2.ge("create_time",firstday);
        int requireAcceptCountByMonth = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper2);
        // 获取月退单勘探数
        QueryWrapper<SysWorkDispatch> sysWorkDispatchQueryWrapper4 =new QueryWrapper<>();
        sysWorkDispatchQueryWrapper4.eq("manage",realUser.getUserId());
        sysWorkDispatchQueryWrapper4.eq("dispatch_state",2);
        sysWorkDispatchQueryWrapper4.eq("is_recall",1);
        sysOrderDispatchQueryWrapper4.le("create_time",lastDay);
        sysWorkDispatchQueryWrapper4.ge("create_time",firstday);
        int requireRecallCountByMonth = sysWorkDispatchService.count(sysWorkDispatchQueryWrapper4);




        int totalAcceptCount =orderAcceptCount+requireAcceptCount;
        int totalAcceptCountByMonth =requireAcceptCountByMonth+orderAcceptCountByMonth;
        int totalRecallCount = orderRecallCount+requireRecallCount;
        int totalRecallCountByMonth = orderRecallCountByMonth+requireRecallCountByMonth;
        List<Integer> list =new ArrayList<>();
        list.add(totalAcceptCount);
        list.add(totalAcceptCountByMonth);
        list.add(totalRecallCount);
        list.add(totalRecallCountByMonth);
        System.out.println(list);
        // 获取相应的根据季度划分的数据
        List<ArrayList<Integer>> quarterlist= new ArrayList<>();
        // 第一个数据为季度工作单接单数量
        quarterlist.add(CollUtil.newArrayList( jiedanorder1 ,jiedanorder2,jiedanorder3,jiedanorder4));
        quarterlist.add(CollUtil.newArrayList(tuidanorder1,tuidanorder2,tuidanorder3,tuidanorder4));
        quarterlist.add(CollUtil.newArrayList(jiedanrequire1,jiedanrequire2,jiedanrequire3,jiedanrequire4));
        quarterlist.add(CollUtil.newArrayList(tuidanrequire1,tuidanrequire2,tuidanrequire3,tuidanrequire4));
        quarterlist.add(CollUtil.newArrayList(orderAcceptCountByMonth,requireAcceptCountByMonth,orderRecallCountByMonth,requireRecallCountByMonth));
        return R.ok(quarterlist);
    }

    /**
     * 获取作业管理员的工作指标（需求制订，工单完成指标）用于主页的图表展示
     *
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/workManageInfoForChart")
    public R getWorkManageInfoForChart(SysUser sysUser) {
        SysUser realUser=sysUserService.getById(sysUser);
        int order1=0,order2=0,order3=0,order4=0;
        int require1=0,require2=0,require3=0,require4=0;
        // 获取季度完成工作单数

        QueryWrapper<SysWorkOrder> sysWorkOrderQueryWrapper =new QueryWrapper<>();
        sysWorkOrderQueryWrapper.eq("apply_people_id",realUser.getUserId());
        sysWorkOrderQueryWrapper.eq("order_status",6);
        List<SysWorkOrder> workOrderList = sysWorkOrderService.list(sysWorkOrderQueryWrapper);
        for (SysWorkOrder od:workOrderList) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: order1+=1; break;
                case Q2: order2+=1;break;
                case Q3: order3+=1;break;
                case Q4: order4+=1;break;
                default: break;
            }
        }

        QueryWrapper<SysWorkRequire> sysWorkRequireQueryWrapper =new QueryWrapper<>();
        sysWorkRequireQueryWrapper.eq("apply_people_id",realUser.getUserId());
        List<SysWorkRequire> workRequireList = sysWorkRequireService.list(sysWorkRequireQueryWrapper);
        for (SysWorkRequire od:workRequireList) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: require1+=1; break;
                case Q2: require2+=1;break;
                case Q3: require3+=1;break;
                case Q4: require4+=1;break;
                default: break;
            }
        }
        // 获取月新增需求数
        QueryWrapper<SysWorkRequire> sysWorkRequireQueryWrapper2 =new QueryWrapper<>();
        sysWorkRequireQueryWrapper2.eq("apply_people_id",realUser.getUserId());
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstday);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        sysWorkRequireQueryWrapper2.le("create_time",lastDay);
        sysWorkRequireQueryWrapper2.ge("create_time",firstday);

        int requireCountByMonth = sysWorkRequireService.count(sysWorkRequireQueryWrapper2);
        // 获取月完成工单数
        QueryWrapper<SysWorkOrder> sysWorkOrderQueryWrapper2 =new QueryWrapper<>();
        sysWorkOrderQueryWrapper2.eq("apply_people_id",realUser.getUserId());
        sysWorkOrderQueryWrapper2.eq("order_status",6);
        sysWorkOrderQueryWrapper2.le("create_time",lastDay);
        sysWorkOrderQueryWrapper2.ge("create_time",firstday);

        int orderCountByMonth = sysWorkOrderService.count(sysWorkOrderQueryWrapper2);

        // 获取相应的根据季度划分的数据
        List<ArrayList<Integer>> quarterlist= new ArrayList<>();
        // 第一个数据为季度工作单接单数量
        quarterlist.add(CollUtil.newArrayList( require1 ,require2,require3,require4));
        quarterlist.add(CollUtil.newArrayList(order1,order2,order3,order4));
        quarterlist.add(CollUtil.newArrayList(requireCountByMonth,orderCountByMonth));
        return R.ok(quarterlist);
    }
    /**
     * 获取仓库管理人员的工作信息（用于柱状图和饼状图）
     *
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/equipmentWarnInfoForChart")
    public R getEquipmentWarnInfoForChart(SysUser sysUser) {
        SysUser realUser=sysUserService.getById(sysUser);
        int toolWarn1=0,toolWarn2=0,toolWarn3=0,toolWarn4=0;
        int carWarn1=0,carWarn2=0,carWarn3=0,carWarn4=0;
        int carBaoXian1=0,carBaoXian2=0,carBaoXian3=0,carBaoXian4=0;
        // 获取季度预警处理数

        QueryWrapper<SysWarnInfo> sysWarnInfoQueryWrapper =new QueryWrapper<>();
        sysWarnInfoQueryWrapper.eq("handle_id",realUser.getUserId());
        sysWarnInfoQueryWrapper.eq("type","工具");
        sysWarnInfoQueryWrapper.eq("warn_type","试验");
        List<SysWarnInfo> warnInfos = sysWarnInfoService.list(sysWarnInfoQueryWrapper);
        for (SysWarnInfo od:warnInfos) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: toolWarn1+=1; break;
                case Q2: toolWarn2+=1;break;
                case Q3: toolWarn3+=1;break;
                case Q4: toolWarn4+=1;break;
                default: break;
            }
        }

        QueryWrapper<SysWarnInfo> sysWarnInfoQueryWrapper2 =new QueryWrapper<>();
        sysWarnInfoQueryWrapper2.eq("handle_id",realUser.getUserId());
        sysWarnInfoQueryWrapper2.eq("type","车辆");
        sysWarnInfoQueryWrapper2.eq("warn_type","试验");
        List<SysWarnInfo> warnInfos2 = sysWarnInfoService.list(sysWarnInfoQueryWrapper2);
        for (SysWarnInfo od:warnInfos2) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: carWarn1+=1; break;
                case Q2: carWarn2+=1;break;
                case Q3: carWarn3+=1;break;
                case Q4: carWarn4+=1;break;
                default: break;
            }
        }
        QueryWrapper<SysWarnInfo> sysWarnInfoQueryWrapper3 =new QueryWrapper<>();
        sysWarnInfoQueryWrapper3.eq("handle_id",realUser.getUserId());
        sysWarnInfoQueryWrapper3.eq("type","车辆");
        sysWarnInfoQueryWrapper3.eq("warn_type","保险");
        List<SysWarnInfo> warnInfos3 = sysWarnInfoService.list(sysWarnInfoQueryWrapper3);
        for (SysWarnInfo od:warnInfos3) {
            LocalDateTime time=od.getCreateTime();
            Quarter quarter= DateUtil.quarterEnum(Date.from( time.atZone( ZoneId.systemDefault()).toInstant()));
            switch (quarter){
                case Q1: carBaoXian1+=1; break;
                case Q2: carBaoXian2+=1;break;
                case Q3: carBaoXian3+=1;break;
                case Q4: carBaoXian4+=1;break;
                default: break;
            }
        }

        //工具待处理数
        Page page =new Page<SysTools>();
        SysTools sysTools =new SysTools();
        sysTools.setUserUnit(realUser.getDeptId());
        IPage<SysTools> toolWarnInfos= sysToolsService.getToolWarnInfo(page,sysTools);
        int toolWarnCount1 = toolWarnInfos.getRecords().size();
        int toolWarnCount =toolWarnCount1;
        // 车辆待处理数
        SysCar sysCar =new SysCar();
        sysCar.setUserUnit(realUser.getDeptId());
        IPage<SysCar> carIPage= sysCarService.getCarWarnInsuranceInfo(page,sysCar);
        int carWarnCount1 = carIPage.getRecords().size();
        IPage<SysCar> carIPage2= sysCarService.getCarWarnTestInfo(page,sysCar);
        int carWarnCount2 = carIPage2.getRecords().size();
        int carWarnCount =carWarnCount1+carWarnCount2;

        // 获取相应的根据季度划分的数据
        List<ArrayList<Integer>> quarterlist= new ArrayList<>();
        // 第一个数据为季度工作单接单数量
        quarterlist.add(CollUtil.newArrayList( toolWarn1 ,toolWarn2,toolWarn3,toolWarn4));
        quarterlist.add(CollUtil.newArrayList(carWarn1,carWarn2,carWarn3,carWarn4));
        quarterlist.add(CollUtil.newArrayList(carBaoXian1,carBaoXian2,carBaoXian3,carBaoXian4));
        quarterlist.add(CollUtil.newArrayList(toolWarnCount,carWarnCount2,carWarnCount1));
        return R.ok(quarterlist);


    }


    /**
     * 获取库房内工具以及相应的车辆的信息（用于4个数字的处理）
     *
     *
     * @param sysWarehourse 派单
     * @return
     */
    @GetMapping("/equipmentInfoInWareHourse")
    public R getEquipmentInfoInWareHourse(SysWarehourse sysWarehourse) {
        ArrayList<String> toolTypeList= new ArrayList<>();
        ArrayList<String> carTypeList= new ArrayList<>();
        // 从相应的码表之中获取相应去工具类型以及车辆类型的数据，并存到相应的list之中
        List<SysInterface> interfaceList1= sysInterfaceService.selectInterfaceArrayList("tooltype");
        List<SysInterface> interfaceList2= sysInterfaceService.selectInterfaceArrayList("cartype");
        for (SysInterface it: interfaceList1
             ) {
            toolTypeList.add(it.getValue());
        }
        for (SysInterface it: interfaceList2
                ) {
            carTypeList.add(it.getValue());
        }

        int toolTypeFree1=0,toolTypeFree2=0,toolTypeFree3=0,toolTypeFree5=0,toolTypeFree4=0,toolTypeFree6=0;
        int toolTypeBusy1=0,toolTypeBusy2=0,toolTypeBusy3=0,toolTypeBusy4=0,toolTypeBusy5=0,toolTypeBusy6=0;
        int carTypeFree1=0,carTypeFree2=0,carTypeFree3=0,carTypeFree4=0,carTypeFree5=0,carTypeFree6=0;
        int carTypeBusy1=0,carTypeBusy2=0,carTypeBusy3=0,carTypeBusy4=0,carTypeBusy5=0,carTypeBusy6=0;

        // 根据库房id查询相应的工具list,并遍历相应的list,
        // 遍历的时候计算相应的空闲的工具和使用中工具的数量
        QueryWrapper<SysTools> sysToolsQueryWrapper =new QueryWrapper<>();
        sysToolsQueryWrapper.eq("tool_warehourse",sysWarehourse.getWarehourseId());
        List<SysTools> toolsList = sysToolsService.list(sysToolsQueryWrapper);
        for (SysTools tool: toolsList) {
            String type = tool.getBigType();
            String toolState = tool.getUseState();
            switch (toolState) {
                case "空闲中":
                    break;
                case "使用中":
                    break;
                default:
                    break;
            }
            switch (type) {
                case "绝缘紧线器":
                    
                    if("空闲中".equals(toolState)){
                        toolTypeFree1 += 1;
                    }else if("使用中".equals(toolState)){
                        toolTypeBusy1 += 1;
                    }
                    break;
                case "绝缘杆":
                    if("空闲中".equals(toolState)){
                        toolTypeFree2 += 1;
                    }else if("使用中".equals(toolState)){
                        toolTypeBusy2 += 1;
                    }
                    break;
                case "绝缘钳":
                    if("空闲中".equals(toolState)){
                        toolTypeFree3 += 1;
                    }else if("使用中".equals(toolState)){
                        toolTypeBusy3 += 1;
                    }
                    break;
                case "防护手套":
                    if("空闲中".equals(toolState)){
                        toolTypeFree4 += 1;
                    }else if("使用中".equals(toolState)){
                        toolTypeBusy4 += 1;
                    }
                    break;
                case "防护靴":
                    if("空闲中".equals(toolState)){
                        toolTypeFree5 += 1;
                    }else if("使用中".equals(toolState)){
                        toolTypeBusy5 += 1;
                    }
                    break;
                default:
                    break;

            }

        }
        // 根据库房id查询相应的工具list,并遍历相应的list,
        // 遍历的时候计算相应的空闲的工具和使用中工具的数量
        QueryWrapper<SysCar> sysCarQueryWrapper =new QueryWrapper<>();
        sysCarQueryWrapper.eq("warehourse_id",sysWarehourse.getWarehourseId());
        List<SysCar> carList = sysCarService.list(sysCarQueryWrapper);
        for (SysCar car: carList) {
            String type = car.getCarType();
            String carUseState = car.getCarUseState();
            switch (carUseState) {
                case "空闲中":
                    break;
                case "使用中":
                    break;
                default:
                    break;
            }
            switch (type) {
                case "翻斗车":

                    if("空闲中".equals(carUseState)){
                        carTypeFree1 += 1;
                    }else if("使用中".equals(carUseState)){
                        carTypeBusy1 += 1;
                    }
                    break;
                case "吊车":
                    if("空闲中".equals(carUseState)){
                        carTypeFree2 += 1;
                    }else if("使用中".equals(carUseState)){
                        carTypeBusy2 += 1;
                    }
                    break;
                case "曲臂作业车":
                    if("空闲中".equals(carUseState)){
                        carTypeFree3 += 1;
                    }else if("使用中".equals(carUseState)){
                        carTypeBusy3 += 1;
                    }
                    break;
                case "高空作业车":
                    if("空闲中".equals(carUseState)){
                        carTypeFree4 += 1;
                    }else if("使用中".equals(carUseState)){
                        carTypeBusy4 += 1;
                    }
                    break;
                case "轨道行走作业车":
                    if("空闲中".equals(carUseState)){
                        carTypeFree5 += 1;
                    }else if("使用中".equals(carUseState)){
                        carTypeBusy5 += 1;
                    }
                    break;
                default:
                    break;

            }

        }


        List<ArrayList<String>> quarterlist= new ArrayList<>();
        // 工具类型list
        quarterlist.add(toolTypeList);
        // 空闲工具
        quarterlist.add(CollUtil.newArrayList( Integer.toString(toolTypeFree1) ,Integer.toString(toolTypeFree2),Integer.toString(toolTypeFree3),Integer.toString(toolTypeFree4),Integer.toString(toolTypeFree5)));
        // 使用中工具list
        quarterlist.add(CollUtil.newArrayList( Integer.toString(toolTypeBusy1) ,Integer.toString(toolTypeBusy2),Integer.toString(toolTypeBusy3),Integer.toString(toolTypeBusy4),Integer.toString(toolTypeBusy5)));
        // 各个工具的类型的数量
        quarterlist.add(CollUtil.newArrayList( Integer.toString(toolTypeBusy1+toolTypeFree1) ,Integer.toString(toolTypeBusy2+toolTypeFree2),Integer.toString(toolTypeBusy3+toolTypeFree3),Integer.toString(toolTypeBusy4+toolTypeFree4),Integer.toString(toolTypeBusy5+toolTypeFree5)));
        // 车辆类型list
        quarterlist.add(carTypeList);
        // 空闲工具
        quarterlist.add(CollUtil.newArrayList( Integer.toString(carTypeFree1) ,Integer.toString(carTypeFree2),Integer.toString(carTypeFree3),Integer.toString(carTypeFree4),Integer.toString(carTypeFree5)));
        // 使用中工具list
        quarterlist.add(CollUtil.newArrayList( Integer.toString(carTypeBusy1) ,Integer.toString(carTypeBusy2),Integer.toString(carTypeBusy3),Integer.toString(carTypeBusy4),Integer.toString(carTypeBusy5)));
        // 各个工具的类型的数量
        quarterlist.add(CollUtil.newArrayList( Integer.toString(carTypeBusy1+carTypeFree1) ,Integer.toString(carTypeBusy2+carTypeFree2),Integer.toString(carTypeBusy3+carTypeFree3),Integer.toString(carTypeBusy4+carTypeFree4),Integer.toString(carTypeBusy5+carTypeFree5)));

        return R.ok(quarterlist);
    }
}
