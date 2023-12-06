package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysWorkRequire;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 需求表 服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-19
 */
public interface ISysWorkRequireService extends IService<SysWorkRequire> {
    /*
    获取需求信息，并进行相应的分页显示
     */
    IPage<SysWorkRequire> getRequireInfoByPage(Page page, SysWorkRequire sysWorkRequire);
    /*
  获取需求详细信息
   */
   SysWorkRequire getSingleRequireInfo( SysWorkRequire sysWorkRequire);
    /*
    工人获取需求信息，并进行相应的分页显示
     */
    IPage<SysWorkRequire> getWorkerRequireInfoByPage(Page page, SysWorkRequire sysWorkRequire);
    /*

     发布需求信息
     */
    Integer submitrequire(List<Integer> ids) ;
    /*
    撤回需求信息
   */
    Integer recallrequire(List<Integer> ids) ;
}
