package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWarehourse;
import com.xd.pre.modules.sys.mapper.SysWarehourseMapper;
import com.xd.pre.modules.sys.service.ISysWarehourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 库房表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-06
 */
@Service
public class SysWarehourseServiceImpl extends ServiceImpl<SysWarehourseMapper, SysWarehourse> implements ISysWarehourseService {

    @Override
    public List<SysWarehourse> getWareInfo(@Param("unitId") Integer unitId , @Param("teamId") Integer teamId) {
        return baseMapper.findWareInfo(unitId,teamId);
    }

     /*
     分页显示库房信息
      */
    @Override
    public IPage<SysWarehourse> getWareFullInfo(Page page, SysWarehourse sysWarehourse) {

       return  baseMapper.getWareFullInfo( page, sysWarehourse ,new DataScope());
    }

    @Override
    public SysWarehourse getSingleWarehourseInfo(SysWarehourse sysWarehourse) {
        return baseMapper.getSingleWareInfo(sysWarehourse ,new DataScope());
    }
}
