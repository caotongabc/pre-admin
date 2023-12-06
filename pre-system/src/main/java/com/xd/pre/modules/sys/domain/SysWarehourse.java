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
 * 库房表
 * </p>
 *
 * @author caotong
 * @since 2022-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWarehourse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 库房id
     */
    @TableId(value = "warehourse_id", type = IdType.AUTO)
    private Integer warehourseId;

    /**
     * 库房名称
     */
    private String warehourseName;

    /**
     * 单位
     */
    private Integer unitId;

    /**
     * 班组
     */
    private Integer teamId;

    /**
     * 库房地址
     */
    private String warehourseAddress;

    /**
     * 库房状态
     */
    private Integer warehourseState;

    /**
     * 保管人
     */
    private Integer baoguanPeople;

    /**
     * 保管人电话
     */
    private String peopleTel;

    /**
     * 备注
     */
    private String warehourseNote;

    /**
     * 库房照片
     */
    private String warehoursePhoto;

    private Integer idDelete;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 非数据库字段
     */
    @TableField(exist = false)
    private String unitName;

    @TableField(exist = false)

    private String teamName;
    @TableField(exist = false)
    private Integer userUnit;


    @TableField(exist = false)
    private String peopleName;
    @TableField(exist = false)
    private Integer countTool;
    @TableField(exist = false)
    private Integer countCar;


}
