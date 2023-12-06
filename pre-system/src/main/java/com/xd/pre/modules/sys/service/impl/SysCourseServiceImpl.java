package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.modules.data.strategy.DataScopeContext;
import com.xd.pre.modules.data.tenant.PreTenantContextHolder;
import com.xd.pre.modules.sys.domain.*;
import com.xd.pre.modules.sys.mapper.SysCourseMapper;
import com.xd.pre.modules.sys.mapper.SysTenantMapper;
import com.xd.pre.modules.sys.service.*;
import com.xd.pre.modules.sys.util.PreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
@Service
public class SysCourseServiceImpl  extends ServiceImpl<SysCourseMapper, SysCourse> implements ISysCourseService{
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysRoleMenuService roleMenuService;
    @Autowired
    private DataScopeContext dataScopeContext;


    /**
     * 一般租户授权时
     * 1.保存租户
     * 2.初始化权限相关表
     *
     * @param sysCourse
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveCourse(SysCourse sysCourse) {

        sysCourse.setCreateTime(LocalDateTime.now() );
        sysCourse.setUpdateTime(LocalDateTime.now());
        //this.save(sysCourse);
        Boolean a=this.save(sysCourse);
        return a;
    }

    @Override
    public List<SysCourse> getNormalCourse() {
        return list(Wrappers.<SysCourse>lambdaQuery()
                // 状态为0的
                .eq(SysCourse::getState, 0)
                // 开始时间小于等于现在的时间
                .le(SysCourse::getStartTime, LocalDateTime.now())
                // 结束时间大于等于现在的时间
                .ge(SysCourse::getEndTime, LocalDateTime.now()));
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
