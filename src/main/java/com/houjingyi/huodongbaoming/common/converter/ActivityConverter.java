package com.houjingyi.huodongbaoming.common.converter;

import com.houjingyi.huodongbaoming.common.form.activity.ActivityPublishForm;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.model.vo.ActivityVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 活动 Converter
 *
 * @author yuelong.liang
 */
@Mapper(componentModel = "spring")
public interface ActivityConverter {

    /**
     * 活动 VO
     *
     * @param entity 实体类
     * @return VO
     */
    @Mapping(target = "id", source = "id")
    ActivityVO toVO(Activity entity);

    /**
     * 发布表单转实体类
     *
     * @param form 发布表单
     * @return 实体类
     */
    @Mapping(target = "num", constant = "1")
    @Mapping(target = "status", constant = "0")
    Activity toEntity(ActivityPublishForm form);

}
