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
 * 派单信息表
 * </p>
 *
 * @author caotonf
 * @since 2022-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWorkDispatch implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 需求id
     */
    private String requireId;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dispatchTime;

    /**
     * 是否撤销
     */
    private Integer isRecall;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 处理类型
     */
    private Integer handleType;
    /**
     * 处理原因
     */
    private String handleReason;
    /**
     * 接单、退单时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

    /**
     * 是否完成或者结束
     */
    private Integer isFinish;

    private Integer dispatchState;

    @TableField(exist = false)
    private String jiedanUnitName;
    @TableField(exist = false)
    private String jiedanTeamName;
    @TableField(exist = false)
    private String manageNameReall;
    @TableField(exist = false)
    private String dispatchUserName2;
    @TableField(exist = false)
    private String dispatchUnitName;
}
