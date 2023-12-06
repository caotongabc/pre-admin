package com.xd.pre.modules.sys.mapper;

import com.xd.pre.modules.sys.domain.SysStation;
import com.xd.pre.modules.sys.domain.SysTg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 台区表 Mapper 接口
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
public interface SysTgMapper extends BaseMapper<SysTg> {
    @Select("SELECT * FROM sys_tg WHERE sys_tg.line_id=#{lineId}")
    List<SysTg> getTgInfo(Integer lineId);
}
