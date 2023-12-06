package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Blob;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caotong
 * @since 2022-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTools implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 工具表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工具id
     */
    private String toolId;

    /**
     * 工具名称
     */

    private String toolName;

    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 大类
     */
    private String bigType;

    /**
     * 中类
     */
    private String mediumType;

    /**
     * 小类
     */
    private String samllType;

    /**
     * 图片Blob
     */
    private String toolPhoto;

    /**
     * 库房
     */
    private Integer toolWarehourse;

    /**
     * 使用状态
     */
    private Integer toolState;

    /**
     * 下次试验时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextTime;

    /**
     * 本次试验时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime thisTime;

    /**
     * 单位
     */
    private Integer toolUnit;

    /**
     * 班组
     */
    private Integer toolTeam;
    /**
     * 使用状态
     */
    private String useState;

    /**
     * 购买时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyTime;
    /**
     * 备注
     */
    private String toolNote;

    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String unitName;

    @TableField(exist = false)

    private String teamName;
    @TableField(exist = false)
    private String warehourseName;
    @TableField(exist = false)
    private Integer userUnit;

    @TableField(exist = false)
    private Integer expirationDate;
    @TableField(exist = false)
    private Integer warnNum;

}
