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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 工单派单信息表
 * </p>
 *
 * @author caotong
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrderDispatch implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工作单id
     */
    private String orderId;

    /**
     * 派单人的id
     */
    private Integer dispatchUserId;

    /**
     * 派单的人员名称
     */
    private String dispatchUserName;

    /**
     * 派单的单位id
     */
    private Integer dispathUnitId;

    /**
     * 接单的单位
     */
    private String jiedanUnit;

    /**
     * 接单的班组
     */
    private String jiedanTeam;

    /**
     * 负责人id
     */
    private Integer manage;

    /**
     * 负责人姓名
     */
    private String manageName;

    /**
     * 负责人电话
     */
    private String managePhone;

    /**
     * 派单备注
     */
    private String dispatchNote;

    /**
     * 派单时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dispatchTime;

    /**
     * 是否撤销
     */
    private Integer isRecall;

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
     * 处理类型：0接单，1，退单，2：需求人员退单
     */
    private Integer handleType;

    /**
     * 接单、退单时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

    /**
     * 处理原因
     */
    private String handleReason;

    /**
     * 是否完成，结束
     */
    private Integer isFinish;

    /**
     * 派单状态0未接单，1，已接单，2，已退单,3已回复，4审核通过，5审核不通过）
     */
    private Integer dispatchState;

    @TableField(exist = false)
    private String jiedanUnitName;
    @TableField(exist = false)
    private String jiedanTeamName;
    @TableField(exist = false)
    private String manageNameRecall;
    @TableField(exist = false)
    private String dispatchUserName2;
    @TableField(exist = false)
    private String dispatchUnitName;



}
