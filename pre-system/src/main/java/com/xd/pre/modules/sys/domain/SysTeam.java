package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caotong
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTeam implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "team_id", type = IdType.AUTO)
    private Integer teamId;

    private String teamName;

    private String teamUnit;

    private Integer teamUnitid;

    /**
     * 是否删除
     */
    private Integer idDelete;


}
