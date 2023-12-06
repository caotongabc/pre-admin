package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.sys.domain.SysTools;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.modules.sys.domain.SysUser;
import com.xd.pre.modules.sys.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caotong
 * @since 2022-03-03
 */
public interface ISysToolsService extends IService<SysTools> {
    /**
     * 保存工具
     *
     * @param sysTools
     * @return
     */
    boolean saveTools(SysTools sysTools);


    /**
     * 获取工具
     *
     * @return
     */
    List<SysTools> getNormalTools();
    /**
     * 获取工具转义
     *
     * @return
     */

    IPage<SysTools> getToolInfo2(Page page, SysTools sysTools);
    IPage<SysTools> getToolWarnInfo(Page page, SysTools sysTools);
    /*
    检验工具号是否存在
     */
    Integer isExistToolId(@Param("toolId") Integer toolId , @Param("id") Integer id);
    /*
    获取单个工具信息
     */
    SysTools getSingleToolInfo( SysTools sysTools);
    List<SysTools> findToolInfo(@Param("warehourseId") Integer warehourseId,@Param("toolType") String toolType);
}
