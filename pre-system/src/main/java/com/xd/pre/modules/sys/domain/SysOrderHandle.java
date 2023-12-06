package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 工作单处理表
 * </p>
 *
 * @author caotong
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrderHandle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 处理id
     */
    @TableId(value = "handle_id", type = IdType.AUTO)
    private Integer handleId;

    /**
     * 需求id
     */
    private String orderId;

    /**
     * 派单id
     */
    private Integer dispatchId;

    /**
     * 工具1
     */
    private String toolOne;

    /**
     * 工具2
     */
    private String toolTwo;

    /**
     * 工具3
     */
    private String toolThree;

    /**
     * 工具4
     */
    private String toolFour;

    /**
     * 工具5
     */
    private String toolFive;

    /**
     * 工具6
     */
    private String toolSix;

    /**
     * 领取装备时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime linquTime;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 装备领取人
     */
    private String manage;
    /**
     * 领取装备时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arriveTime;
    /**
     * 现场情况
     */
    private String liveCondition;
    private String workerPhoto;
    private String finishNote;
    private String workAfterPhoto;
    private String workBeforePhoto;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startWorkTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishWorkTime;

    /**
     * 勘查开始时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime watchStartTime;

    /**
     * 勘察结束时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime watchEndTime;

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

    /**
     * 勘察结果
     */
    private String watchResult;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 回复时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime feedbackTime;

    /**
     * 回复人id
     */
    private Integer feedbackPeopleId;

    /**
     * 回复人名称
     */
    private String feedbackPeopleName;

    /**
     * 多张图片信息
     */
    private String multiPhoto;


}
