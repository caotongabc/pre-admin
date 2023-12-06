package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.xd.pre.modules.sys.mapper.SysWorkRequireMapper;
import com.xd.pre.modules.sys.service.ISysWorkRequireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 需求表 服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-19
 */
@Service
public class SysWorkRequireServiceImpl extends ServiceImpl<SysWorkRequireMapper, SysWorkRequire> implements ISysWorkRequireService {
   /*
    查询需求信息，并分页显示
    */
    @Override
    public IPage<SysWorkRequire> getRequireInfoByPage(Page page, SysWorkRequire sysWorkRequire) {
        return baseMapper.getRequireInfo(page,sysWorkRequire,new DataScope());
    }

    @Override
    public SysWorkRequire getSingleRequireInfo(SysWorkRequire sysWorkRequire) {
        return baseMapper.getSingleRequireInfo(sysWorkRequire ,new DataScope());
    }

    @Override
    public IPage<SysWorkRequire> getWorkerRequireInfoByPage(Page page, SysWorkRequire sysWorkRequire) {
        return baseMapper.getWorkerRequireInfo(page,sysWorkRequire,new DataScope());
    }

    /*
    发布需求信息
     */
    @Override
    public Integer submitrequire(List<Integer> ids) {
        Iterator<Integer> iterator = ids.iterator();
        int count=0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            baseMapper.submitRequire(id);
            count++;
        }
        return count;
    }
    /*
    撤回需求信息
     */

    @Override
    public Integer recallrequire(List<Integer> ids) {
        Iterator<Integer> iterator = ids.iterator();
        int count=0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            baseMapper.recallRequire(id);
            count++;
        }
        return count;
    }
}
