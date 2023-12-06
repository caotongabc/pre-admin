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
 * 预警策略
 * </p>
 *
 * @author caotong
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWarnStrategy implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单位
     */
    private Integer unitId;

    /**
     * 装备类型
     */
    private String type;

    /**
     * 装备小类
     */
    private String subType;

    /**
     * 预警类别
     */
    private String warnType;

    /**
     * 有效期
     */
    private Integer expirationDate;

    /**
     * 提前预警期
     */
    private Integer warnNum;

    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 非数据库字段
     */
    @TableField(exist = false)
    private String unitName;

}
