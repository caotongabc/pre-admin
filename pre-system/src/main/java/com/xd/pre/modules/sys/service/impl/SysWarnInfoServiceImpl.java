package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysCar;
import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.domain.SysWarnInfo;
import com.xd.pre.modules.sys.mapper.SysWarnInfoMapper;
import com.xd.pre.modules.sys.service.ISysCarService;
import com.xd.pre.modules.sys.service.ISysToolsService;
import com.xd.pre.modules.sys.service.ISysWarnInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.time.LocalDateTime;

/**
 * <p>
 * 试验信息表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-10
 */
@Service
public class SysWarnInfoServiceImpl extends ServiceImpl<SysWarnInfoMapper, SysWarnInfo> implements ISysWarnInfoService {
    @Autowired
    private ISysToolsService sysToolsService;
    @Autowired
    private ISysCarService sysCarService;
    /*
    报存工具试验信息，修改工具的试验信息以及相应的状态
     */
    @Override
    public boolean saveToolWarnInfo(SysWarnInfo sysWarnInfo) {

        String result=sysWarnInfo.getWarnResult();
        Integer toolState;
        if (result.equals("合格")){
            toolState=0;
        }
        else {
            toolState=9;
        }
        sysWarnInfo.setCreateTime(LocalDateTime.now());
        boolean b=sysToolsService.update(Wrappers.<SysTools>lambdaUpdate()
                .set(SysTools::getNextTime,sysWarnInfo.getNextWarnTime())
                .set(SysTools::getThisTime,sysWarnInfo.getThisWarnTime())
                .set(SysTools::getToolState,toolState)
                .eq(SysTools::getToolId,Integer.parseInt(sysWarnInfo.getEquipmentId()))
         );


        Boolean a=this.save(sysWarnInfo);
        return a;
    }
    /*
           保存车辆的试验信息
     */
    @Override
    public boolean saveCarWarnTestInfo(SysWarnInfo sysWarnInfo) {
        String result=sysWarnInfo.getWarnResult();
        SysCar sysCar=sysCarService.getOne(Wrappers.<SysCar>lambdaQuery()
          .eq(SysCar::getCarNumber,sysWarnInfo.getEquipmentId()));
        Integer yuanTestState =sysCar.getTestState();
        Integer carState=sysCar.getCarState();
        Integer yuanInsuranceState =sysCar.getInsuranceState();

        if (result.equals("合格")){
            yuanTestState=1;
            if(yuanInsuranceState == 1) {// 保险合格，试验合格
                carState=0;
            }
        } else {
            yuanTestState=2; //试验不合格
            carState=9;
        }
        sysWarnInfo.setCreateTime(LocalDateTime.now());
        boolean b=sysCarService.update(Wrappers.<SysCar>lambdaUpdate()
        .set(SysCar::getNextTestTime,sysWarnInfo.getNextWarnTime())
        .set(SysCar::getThisTestTime,sysWarnInfo.getThisWarnTime())
        .set(SysCar::getCarState,carState)
         .set(SysCar::getTestState,yuanTestState) .set(SysCar::getInsuranceState,yuanInsuranceState)
        .eq(SysCar::getCarNumber,sysWarnInfo.getEquipmentId())
        );
        Boolean a=this.save(sysWarnInfo);
        return a;
    }
    /*
    保存车辆的保险信息
     */
    @Override
    public boolean saveCarWarnINsuranceInfo(SysWarnInfo sysWarnInfo) {
        sysWarnInfo.setCreateTime(LocalDateTime.now());
        String result=sysWarnInfo.getWarnResult();
        SysCar sysCar=sysCarService.getOne(Wrappers.<SysCar>lambdaQuery()
                .eq(SysCar::getCarNumber,sysWarnInfo.getEquipmentId()));
        Integer carState=sysCar.getCarState();
        Integer yuanTestState =sysCar.getTestState();
        Integer yuanInsuranceState =sysCar.getInsuranceState();

        if (result.equals("合格")){
            yuanInsuranceState=1;
            if(yuanTestState == 1) {// 保险合格，试验合格
                carState=0;
            }
        } else {
            yuanInsuranceState=2;//保险不合格
            carState=8;
        }
        boolean b=sysCarService.update(Wrappers.<SysCar>lambdaUpdate()
                .set(SysCar::getNextInsuranceTime,sysWarnInfo.getNextWarnTime())
                .set(SysCar::getThisInsuranceTime,sysWarnInfo.getThisWarnTime())
                .set(SysCar::getCarState,carState) .set(SysCar::getTestState,yuanTestState) .set(SysCar::getInsuranceState,yuanInsuranceState)
                .eq(SysCar::getCarNumber,sysWarnInfo.getEquipmentId())
        );
        Boolean a=this.save(sysWarnInfo);
        return a;
    }

    @Override
    public IPage<SysWarnInfo> getToolWarnDetailInfo(Page page, SysWarnInfo sysWarnInfo) {
        return baseMapper.getToolWarnDetailInfo(page,sysWarnInfo,new DataScope());
    }

    @Override
    public IPage<SysWarnInfo> getCarWarnTestDetailInfo(Page page, SysWarnInfo sysWarnInfo) {
        return baseMapper.getCarWarnTestDetailInfo(page,sysWarnInfo,new DataScope());
    }

    @Override
    public IPage<SysWarnInfo> getCarWarnInsuranceDetailInfo(Page page, SysWarnInfo sysWarnInfo) {
        return baseMapper.getCarlWarnInsuranceDetailInfo(page,sysWarnInfo,new DataScope());
    }
}
