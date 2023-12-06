package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 需求勘察处理表
 * </p>
 *
 * @author caotong
 * @since 2022-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRequireHandle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 处理id
     */
    @TableId(value = "handle_id", type = IdType.AUTO)
    private Integer handleId;

    /**
     * 需求id
     */
    private String requireId;
    /*
    回复人员id
     */
    private Integer feedbackPeopleId;
    /*
    回复人员名称
     */
    private String feedbackPeopleName;

    /**
     * 派单id
     */
    private Integer dispatchId;

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
     * 勘察回复时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime feedbackTime;

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
    private String multiPhoto;

    @TableField(exist = false)
    private List<photo> picList;

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


}
