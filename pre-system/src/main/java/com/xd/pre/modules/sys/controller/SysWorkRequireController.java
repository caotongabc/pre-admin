package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysRequireHandle;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.xd.pre.modules.sys.domain.SysWorkOrder;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.xd.pre.modules.sys.service.ISysWorkRequireService;
import com.xd.pre.modules.sys.service.impl.SysRequireHandleServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkDispatchServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkOrderServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 需求表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/workRequire")
public class SysWorkRequireController {
    @Autowired
    private ISysWorkRequireService sysWorkRequireService;
    @Autowired
    private SysWorkDispatchServiceImpl sysWorkDispatchService;
    @Autowired
    private SysWorkOrderServiceImpl sysWorkOrderService;
    @Autowired
    private SysRequireHandleServiceImpl sysRequireHandleService;
    /**
     * 自定义特殊分页查询
     *
     * @param page      分页对象
     * @param sysWorkRequirer 工作需求
     * @return
     */
    @GetMapping("/page")
    public R getSysOrderCoursePageTest(Page page, SysWorkRequire sysWorkRequirer) {
        return R.ok(sysWorkRequireService.getRequireInfoByPage(page, sysWorkRequirer));

    }
    /**
     * 自定义特殊分页查询
     *
     * @param sysWorkRequirer 工作需求
     * @return
     */
    @GetMapping("/requireInfoOne")
    public R getSingleRequireInfo( SysWorkRequire sysWorkRequirer) {
        return R.ok(sysWorkRequireService.getSingleRequireInfo(sysWorkRequirer));

    }
    /**
     * 根据id进行查询相应的需求信息
     *
     *
     * @param sysWorkRequirer 工作需求
     * @return
     */
    @GetMapping("/infoOne")
    public R getRequireInfoById( SysWorkRequire sysWorkRequirer) {
        QueryWrapper<SysWorkRequire> wrapper = new QueryWrapper<>();
        wrapper.eq("require_id",sysWorkRequirer.getRequireId());
        return R.ok(sysWorkRequireService.getOne(wrapper));

    }
    /**
     * 工人获取派单的信息
     *
     * @param page      分页对象
     * @param sysWorkRequirer 工作需求
     * @return
     */
    @GetMapping("/work/page")
    public R getWorkerSysOrderCoursePageTest(Page page, SysWorkRequire sysWorkRequirer) {
        return R.ok(sysWorkRequireService.getWorkerRequireInfoByPage(page, sysWorkRequirer));

    }
    /**
     * 新增需求
     *
     * @param sysWorkRequire 需求
     * @return R
     */
    @SysOperaLog(descrption = "新增需求")
    @PostMapping
    public R save(@RequestBody SysWorkRequire sysWorkRequire) {
         sysWorkRequire.setCreateTime(LocalDateTime.now());
         sysWorkRequire.setApplyTime(LocalDateTime.now());
         sysWorkRequire.setRequireStatus(0);
         sysWorkRequire.setIsDelete(0);
        return R.ok(sysWorkRequireService.save(sysWorkRequire));
    }

    /**
     * 修改修改需求
     *
     * @param sysWorkRequire 需求
     * @return R
     */
    @SysOperaLog(descrption = "修改需求")
    @PutMapping
    public R updateById(@RequestBody SysWorkRequire sysWorkRequire) {
        sysWorkRequire.setUpdateTime(LocalDateTime.now());
        return R.ok(sysWorkRequireService.updateById(sysWorkRequire));
    }
    /**
     * 审核需求
     *
     * @param sysWorkRequire 需求
     * @return R
     */
    @SysOperaLog(descrption = "审核操作")
    @PutMapping("/audit")
    public R auditRequireById(@RequestBody SysWorkRequire sysWorkRequire) {
        sysWorkRequire.setUpdateTime(LocalDateTime.now());
        sysWorkRequire.setAuditTime(LocalDateTime.now());
        // 根据审核的结果来及更新派单的信息
        if( "通过".equals(sysWorkRequire.getAuditResult())){
            sysWorkRequire.setRequireStatus(6);
            QueryWrapper<SysWorkDispatch> wrapper = new QueryWrapper<>();
            wrapper.eq("require_id",sysWorkRequire.getRequireId());
            wrapper.eq("dispatch_state",3);
            wrapper.eq("is_recall",0);
            // 查询到相应的派单信息之后，把派单的状态改变并进行相应的更新操作
            SysWorkDispatch sysWorkDispatch= sysWorkDispatchService.getOne(wrapper);
            sysWorkDispatch.setDispatchState(4);
            sysWorkDispatch.setUpdateTime(LocalDateTime.now());
            // 根据派单主键进行相应的更新，把派单状态变成相应的已审核成功
            sysWorkDispatchService.updateById(sysWorkDispatch);
            //  审核成功后，把相应的数据添加到相应的工单表之中
            SysWorkOrder sysWorkOrder =new SysWorkOrder();
            BeanUtils.copyProperties(sysWorkRequire, sysWorkOrder);
            QueryWrapper<SysRequireHandle> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("require_id",sysWorkRequire.getRequireId());
            SysRequireHandle sysRequireHandle1=sysRequireHandleService.getOne(wrapper3);
            //时间（精确到毫秒）
            DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String localDate = LocalDateTime.now().format(ofPattern);
            //随机数
            String randomNumeric = RandomStringUtils.randomNumeric(3);
            sysWorkOrder.setOrderId(localDate+randomNumeric);
            sysWorkOrder.setOrderStatus(1);
            sysWorkOrder.setAuditPeople("-1");
            sysWorkOrder.setAuditReason("-1");
            sysWorkOrder.setAuditResult("-1");
            sysWorkOrder.setCreateTime(LocalDateTime.now());
            sysWorkOrder.setUpdateTime(LocalDateTime.now());
            sysWorkOrder.setWorkCondition(sysRequireHandle1.getWorkCondition());
            sysWorkOrder.setWorkEnvironment(sysRequireHandle1.getWorkEnvironment());
            sysWorkOrder.setSafeMeasures(sysRequireHandle1.getSafeMeasures());
            sysWorkOrder.setLivePhoto(sysRequireHandle1.getLivePhoto());
            sysWorkOrder.setDangerPoint(sysRequireHandle1.getDangerPoint());
            sysWorkDispatch.setCreateTime(LocalDateTime.now());
            sysWorkOrderService.save(sysWorkOrder);




        }
        else if( "不通过".equals(sysWorkRequire.getAuditResult())){
            sysWorkRequire.setRequireStatus(7); //设置需求状态为不通过
            // 查询相应的派单状态为3，待审核，且未撤单，同时需求标号对应
            QueryWrapper<SysWorkDispatch> wrapper = new QueryWrapper<>();
            wrapper.eq("require_id",sysWorkRequire.getRequireId());
            wrapper.eq("dispatch_state",3);
            wrapper.eq("is_recall",0);
            SysWorkDispatch sysWorkDispatch= sysWorkDispatchService.getOne(wrapper);
            sysWorkDispatch.setDispatchState(5);
            sysWorkDispatch.setUpdateTime(LocalDateTime.now());
            // 根据派单主键进行相应的更新，把派单状态变成相应的已审核不成功
            sysWorkDispatchService.updateById(sysWorkDispatch);
        }
        return R.ok(sysWorkRequireService.updateById(sysWorkRequire));
    }

    /**
     * 通过id删除需求
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
    /**
     * 通过id批量发布需求
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "批量发布需求")
    @PostMapping("/submitList")
    public R submitRequireByIds(@RequestBody List<Integer> id) {
        QueryWrapper<SysWorkRequire> wrapper = new QueryWrapper<>();

        return R.ok(sysWorkRequireService.submitrequire(id));

    }
    /**
     * 通过id批量批量撤销需求
     *
     * @param id id
     * @return R
     */
    @SysOperaLog(descrption = "批量撤销需求")
    @PostMapping("/recallList")
    public R recallRequireByIds(@RequestBody List<Integer> id) {
        QueryWrapper<SysWorkRequire> wrapper = new QueryWrapper<>();

        return R.ok(sysWorkRequireService.recallrequire(id));

    }
}

