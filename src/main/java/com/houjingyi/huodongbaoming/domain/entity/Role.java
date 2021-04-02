package com.houjingyi.huodongbaoming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * hd_role
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hd_role")
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色排序
     */
    private Integer sequence;

}