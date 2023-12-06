package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 台区表
 * </p>
 *
 * @author caotong
 * @since 2022-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTg implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 台区id
     */
    @TableId(value = "tg_id", type = IdType.AUTO)
    private Integer tgId;

    /**
     * 台区名称
     */
    private String tgName;

    /**
     * 线路id
     */
    private Integer lineId;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer isDelete;


}
