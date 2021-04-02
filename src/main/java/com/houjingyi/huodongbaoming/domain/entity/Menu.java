package com.houjingyi.huodongbaoming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseLogicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单实体类
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hd_menu")
public class Menu extends BaseLogicEntity {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 序号
     */
    private Integer sequence;

}
