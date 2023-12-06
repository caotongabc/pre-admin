package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 试验信息表
 * </p>
 *
 * @author caotong
 * @since 2022-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWarnInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "warn_id", type = IdType.AUTO)
    private Integer warnId;

    /**
     * 装备id
     */
    private String equipmentId;

    /**
     * 装备类型
     */
    private String type;

    /**
     * 装备小类
     */
    private String subType;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 预警类别
     */
    private String warnType;

    /**
     * 本次预警时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime thisWarnTime;

    /**
     * 下次预警时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextWarnTime;

    /**
     * 试验状态（合格，不合格）
     */
    private Integer warnState;
    /**
     * 处理人id
     */
    private Integer handleId;
    /**
     * 试验人员
     */
    private String warnPeople;

    /**
     * 试验单位
     */
    private String warnUnit;

    /**
     * 试验结果
     */
    private String warnResult;

    /**
     * 备注
     */
    private String warnNote;

    @TableField(exist = false)
    private String equipmentName;
}
