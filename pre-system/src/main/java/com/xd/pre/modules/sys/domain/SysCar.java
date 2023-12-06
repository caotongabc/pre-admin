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
 * 车辆信息表
 * </p>
 *
 * @author caotong
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysCar implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 所属单位
     */
    private Integer unitId;

    /**
     * 所属班组
     */
    private Integer teamId;

    /**
     * 库房
     */
    private Integer warehourseId;

    /**
     * 车辆类型
     */
    private String carType;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    /**
     * 这次试验时间
     */
    private LocalDateTime thisTestTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    /**
     * 下次试验时间
     */
    private LocalDateTime nextTestTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    /**
     * 这次保险时间
     */
    private LocalDateTime thisInsuranceTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    /**
     * 下次保险时间
     */
    private LocalDateTime nextInsuranceTime;

    /**
     * 备注
     */
    private String carNote;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    /**
     * 购置时间
     */
    private LocalDateTime buyTime;

    /**
     * 状态
     */
    private Integer carState;
    /**
     * 试验状态
     */
    private Integer testState;
    /**
     * 保险状态
     */
    private Integer insuranceState;

    /**
     * 车的使用状态
     */
    private String carUseState;

    /**
     * 车长
     */
    private Float carLength;

    /**
     * 车宽
     */
    private Float carWidth;

    /**
     * 车高
     */
    private Float carHeight;

    /**
     * 车重量
     */
    private Float carWeight;

    /**
     * 载重量
     */
    private Float carZaizhong;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Integer isDelete;

    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private Integer userUnit;


    @TableField(exist = false)

    private String teamName;
    @TableField(exist = false)
    private String warehourseName;


}
