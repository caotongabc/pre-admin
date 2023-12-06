package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysCar;
import com.xd.pre.modules.sys.mapper.SysCarMapper;
import com.xd.pre.modules.sys.service.ISysCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车辆信息表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-13
 */
@Service
public class SysCarServiceImpl extends ServiceImpl<SysCarMapper, SysCar> implements ISysCarService {

    @Override
    public IPage<SysCar> getCarInfo2(Page page, SysCar sysCar) {
        return baseMapper.getCarInfo(page,sysCar,new DataScope());
    }

    @Override
    public IPage<SysCar> getCarWarnTestInfo(Page page, SysCar sysCar) {
        return baseMapper.getCarWarnTestInfo(page,sysCar,new DataScope());
    }

    @Override
    public IPage<SysCar> getCarWarnInsuranceInfo(Page page, SysCar sysCar) {
        return baseMapper.getCarWarnInsuranceInfo(page,sysCar,new DataScope());
    }

    @Override
    public Integer isExistCarNmber(String carNumber, Integer id) {
        return baseMapper.isExistCars(carNumber,id);
    }

    @Override
    public SysCar getCarDetalsInfo(SysCar sysCar) {
        return baseMapper.getCarDetailInfo(sysCar ,new DataScope());
    }

    @Override
    public List<SysCar> getCarInfo3(Integer unitId, Integer teamId, Integer warehourseId, String carType) {
        return baseMapper.findCarInfo(unitId,teamId,warehourseId,carType);
    }
}
