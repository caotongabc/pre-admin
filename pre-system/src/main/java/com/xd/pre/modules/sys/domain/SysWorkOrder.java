package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 工作单表
 * </p>
 *
 * @author caotong
 * @since 2022-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWorkOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工作单编号
     */
    private String orderId;
    /**
     * 关联需求编号
     */
    private String requireId;

    /**
     * 工作单状态
     */
    private Integer orderStatus;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 工程类别
     */
    private String projectType;

    /**
     * 需求类型
     */
    private String requireType;

    /**
     * 变电站id
     */
    private Integer stationId;

    /**
     * 线路id
     */
    private Integer lineId;

    /**
     * 台区id
     */
    private String tgId;

    /**
     * 工作地点
     */
    private String workAddress;

    /**
     * 工作内容
     */
    private String workContent;

    /**
     * 申请单位名称
     */
    @TableField("apply_unitName")
    private String applyUnitname;

    /**
     * 申请单位
     */
    private Integer applyUnit;

    /**
     * 申请人
     */
    private String applyPeople;

    /**
     * 申请人id
     */
    private Integer applyPeopleId;

    /**
     * 申请人电话
     */
    private String applyPeopletel;

    /**
     * 工作条件
     */
    private String workCondition;

    /**
     * 工作环境
     */
    private String workEnvironment;

    /**
     * 危险点
     */
    private String dangerPoint;

    /**
     * 安全方法
     */
    private String safeMeasures;

    /**
     * 现场照片
     */
    private String livePhoto;
    private String auditPeople;
    private String auditResult;
    private String auditReason;


    /**
     * 申请时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;

    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startapplytime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endapplytime;

    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private String stationName;
    @TableField(exist = false)
    private Integer manage;
    @TableField(exist = false)
    private String lineName;
    @TableField(exist = false)
    private String tgName;
    @TableField(exist = false)
    private Integer userUnit;




}
