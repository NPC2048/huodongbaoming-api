package com.houjingyi.huodongbaoming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 活动实体类
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hd_activity")
public class Activity extends BaseEntity {

    /**
     * 活动标题
     */
    private String title;

    /**
     * 人数限制
     */
    private Integer limitNum;

    /**
     * 已报名人数
     */
    private Integer num;

    /**
     * 活动描述
     */
    @TableField("content")
    private String content;

    /**
     * 地区名称
     */
    private String acAreaName;

    /**
     * 活动地址编码
     */
    private String acAreaCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 发布活动的用户
     */
    private Long publishUser;

    /**
     * 活动日期
     */
    private LocalDateTime date;

    /**
     * 活动状态
     *
     * @see com.houjingyi.huodongbaoming.common.enums.ActivityStatusEnums
     */
    private Byte status;

}
