package com.xd.pre.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysRequireHandle;
import com.xd.pre.modules.sys.domain.SysWorkDispatch;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.xd.pre.modules.sys.service.impl.SysRequireHandleServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkDispatchServiceImpl;
import com.xd.pre.modules.sys.service.impl.SysWorkRequireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 需求勘察处理表 前端控制器
 * </p>
 *
 * @author caotong
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/requireHandle")
public class SysRequireHandleController {
    @Autowired
    SysWorkDispatchServiceImpl sysWorkDispatchService;
    @Autowired
    SysRequireHandleServiceImpl sysRequireHandleService;
    @Autowired
    SysWorkRequireServiceImpl sysWorkRequireService;

    /**
     * 新增回复信息
     *
     * @param sysRequireHandle 派单
     * @return R
     */
    @SysOperaLog(descrption = "新增勘探回复信息")
    @PostMapping
    public R saveFeedback(@RequestBody SysRequireHandle sysRequireHandle) {
        // 更新需求的信息
        UpdateWrapper<SysWorkRequire> wrapper = new UpdateWrapper<>();
        wrapper.set("require_status",5);
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.eq("require_id",sysRequireHandle.getRequireId());
        Boolean count1=sysWorkRequireService.update(wrapper);

        // 保存回复信息
        //获取相应的派单信息(这里主要是把相应的信息给)，
        SysWorkDispatch sysWorkDispatch = new SysWorkDispatch();
        sysWorkDispatch.setRequireId(sysRequireHandle.getRequireId());
        sysWorkDispatch.setDispatchState(1);
        sysWorkDispatch.setIsRecall(0);
        SysWorkDispatch ReallsysWorkDispatch= sysWorkDispatchService.getRecAcceptDispatchInfoOne(sysWorkDispatch);
        // 更新相应的处理信息，把相应的判单的id,以及相应的接单人，负责人都相应的进行添加到回复信息之中
        sysRequireHandle.setDispatchId(ReallsysWorkDispatch.getId());
        sysRequireHandle.setFeedbackTime(LocalDateTime.now());
        sysRequireHandle.setFeedbackPeopleId(ReallsysWorkDispatch.getManage());
        sysRequireHandle.setFeedbackPeopleName(ReallsysWorkDispatch.getManageName());
        System.out.println(sysRequireHandle.getRequireId());
        sysRequireHandle.setCreateTime(LocalDateTime.now());
        // 回复时更新派单状态未相应的已回复
        UpdateWrapper<SysWorkDispatch> wrapper3 = new UpdateWrapper<>();
        wrapper3.set("dispatch_state",3);
        wrapper3.set("update_time", LocalDateTime.now());
        wrapper3.eq("require_id",sysRequireHandle.getRequireId());
        wrapper3.eq("dispatch_state",1);
        wrapper3.eq("is_recall",0);
        Boolean count=sysWorkDispatchService.update(wrapper3);
        return R.ok(sysRequireHandleService.save(sysRequireHandle));
    }
    /**
     * 修改回复信息 (也就是复勘操作)
     *
     * @param sysRequireHandle 派单
     * @return R
     */
    @SysOperaLog(descrption = "复勘")
    @PutMapping("watchAgain")
    public R updateFeedback(@RequestBody SysRequireHandle sysRequireHandle) {
        // 更新需求的信息,把状态设置未待审核
        UpdateWrapper<SysWorkRequire> wrapper = new UpdateWrapper<>();
        wrapper.set("require_status",5);
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.eq("require_id",sysRequireHandle.getRequireId());
        Boolean count=sysWorkRequireService.update(wrapper);
        // 保存回复信息

        //获取相应的派单信息，在复勘之后把相应的派单状态变成已回复
        SysWorkDispatch sysWorkDispatch = new SysWorkDispatch();
        sysWorkDispatch.setRequireId(sysRequireHandle.getRequireId());
        sysWorkDispatch.setDispatchState(5);
        sysWorkDispatch.setIsRecall(0);
        SysWorkDispatch ReallsysWorkDispatch= sysWorkDispatchService.getRecAcceptDispatchInfoOne(sysWorkDispatch);
        ReallsysWorkDispatch.setDispatchState(3);

         Boolean b =sysWorkDispatchService.updateById(ReallsysWorkDispatch);
        System.out.println("修改派单信息是否成功"+b);
        // 更新相应的处理信息（数据来源于传入的参数）
        sysRequireHandle.setUpdateTime(LocalDateTime.now());
        if(("").equals(sysRequireHandle.getMultiPhoto())){
            sysRequireHandle.setMultiPhoto("");
        }

        return R.ok(sysRequireHandleService.updateById(sysRequireHandle));
    }

    /**
     * 获取需求的回复信息
     *
     *
     * @param sysRequireHandle 派单
     * @return
     */
    @GetMapping("/Info")
    public R getHandleInfoInHandlePage(SysRequireHandle sysRequireHandle) {
        QueryWrapper<SysRequireHandle> wrapper =new QueryWrapper<>();
        wrapper.eq("require_id",sysRequireHandle.getRequireId());
        return R.ok(sysRequireHandleService.getOne(wrapper));
    }
    /**
     * 修改回复信息（复勘）
     *
     * @param sysRequireHandle 需求
     * @return R
     */
    @SysOperaLog(descrption = "修改回复信息（复勘）")
    @PutMapping("/daudva")
    public R updateById(@RequestBody SysRequireHandle sysRequireHandle) {

        sysRequireHandle.setUpdateTime(LocalDateTime.now());

        return R.ok(sysRequireHandleService.updateById(sysRequireHandle));
    }
}

