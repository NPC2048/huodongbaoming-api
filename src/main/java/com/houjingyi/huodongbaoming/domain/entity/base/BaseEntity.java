package com.houjingyi.huodongbaoming.domain.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author yuelong.liang
 */
@Data
public abstract class BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建用户da
     */
    @TableField("create_user")
    private Long createUser;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_user")
    private Long updateUser;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
