package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 线路表
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLine implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 线路id
     */
    @TableId(value = "line_id", type = IdType.AUTO)
    private Integer lineId;

    /**
     * 线路名称
     */
    private String lineName;

    /**
     * 变电站id
     */
    private Integer stationId;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer isDelete;


}
