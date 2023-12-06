package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope;
import com.xd.pre.modules.data.strategy.DataScopeContext;
import com.xd.pre.modules.sys.domain.SysMenu;
import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.mapper.SysToolsMapper;
import com.xd.pre.modules.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caotong
 * @since 2022-03-03
 */
@Service
public class SysToolsServiceImpl extends ServiceImpl<SysToolsMapper, SysTools> implements ISysToolsService {
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysUserService userService;
    private final ISysRoleService roleService;
    @Autowired
    private ISysRoleMenuService roleMenuService;
    @Autowired
    private DataScopeContext dataScopeContext;

    @Autowired
    public SysToolsServiceImpl(ISysRoleService roleService) {
        this.roleService = roleService;
    }


    /**
     * 一般租户授权时
     * 1.保存租户
     * 2.初始化权限相关表
     *
     * @param sysTools
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTools(SysTools sysTools) {

        sysTools.setCreateTime(LocalDateTime.now() );
        sysTools.setUpdateTime(LocalDateTime.now());
        sysTools.setToolState(0);
        sysTools.setUseState("空闲中");
        //this.save(sysCourse);
        Boolean a=this.save(sysTools);
        return a;
    }
    @Override
    public IPage<SysTools> getToolInfo2(Page page, SysTools sysTools){

        return baseMapper.getToolInfo(page,sysTools, new DataScope());
    }

    @Override
    public IPage<SysTools> getToolWarnInfo(Page page, SysTools sysTools) {
        return baseMapper.getToolWarnInfo(page,sysTools, new DataScope());
    }
   /*
   验证工具id是否存在
    */
    @Override
    public Integer isExistToolId(Integer toolId, Integer id) {
        return baseMapper.isExistTools(toolId,id);
    }

    @Override
    public SysTools getSingleToolInfo(SysTools sysTools) {
        return baseMapper.getSingleToolInfo(sysTools ,new DataScope());
    }

    @Override
    public List<SysTools> findToolInfo(Integer warehourseId, String toolType) {
        return baseMapper.findToolInfo(warehourseId,toolType);
    }

    ;
    @Override
    public List<SysTools> getNormalTools() {
        return list(Wrappers.<SysTools>lambdaQuery()
                // 状态为0的
               .eq(SysTools::getToolState, 0)
                // 开始时间小于等于现在的时间
                .le(SysTools::getCreateTime, LocalDateTime.now())
                // 结束时间大于等于现在的时间
               .ge(SysTools::getNextTime, LocalDateTime.now()));
    }


    /**
     * @param str
     * @return
     * @Title
     * @Description 将带有纳秒的时间字符串转换成LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    private List<SysMenu> treeMenuList(List<SysMenu> menuList, int pid) {
        List<SysMenu> childMenu = new ArrayList<>();
        for (SysMenu mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getMenuId());
                childMenu.add(mu);
            }
        }
        return childMenu;
    }
}
