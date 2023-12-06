package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 变电站表
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysStation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 变电站id
     */
    @TableId(value = "station_id", type = IdType.AUTO)
    private Integer stationId;

    /**
     * 所属单位
     */
    private Integer deptId;

    /**
     * 变电站名称
     */
    private String stationName;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer isDelete;


}
