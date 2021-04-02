package com.houjingyi.huodongbaoming.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityQueryForm;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.model.vo.ActivityVO;
import org.apache.ibatis.annotations.Param;

/**
 * 活动 Mapper
 *
 * @author yuelong.liang
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 活动查询
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @return Page
     */
    IPage<ActivityVO> page(IPage<Activity> page, @Param(Constants.WRAPPER) Wrapper<Activity> wrapper);

    /**
     * 分页查询当前登录用户已报名的活动
     *
     * @param page 分页参数
     * @param form 查询条件
     * @return IPage
     */
    IPage<ActivityVO> pageBySignedUp(IPage<Activity> page, @Param("form") ActivityQueryForm form);

}
