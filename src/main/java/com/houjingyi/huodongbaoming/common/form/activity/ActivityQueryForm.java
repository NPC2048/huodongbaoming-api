package com.houjingyi.huodongbaoming.common.form.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.houjingyi.huodongbaoming.common.form.base.BaseQueryForm;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityQueryForm extends BaseQueryForm<Activity> {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 活动状态
     */
    private Byte status;

    /**
     * 用户id
     */
    @JsonIgnore
    private Long userId;

}
