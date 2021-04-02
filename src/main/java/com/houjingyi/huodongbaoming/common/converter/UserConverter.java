package com.houjingyi.huodongbaoming.common.converter;

import com.houjingyi.huodongbaoming.common.form.user.RegisterForm;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.model.vo.UserinfoVO;
import org.mapstruct.Mapper;

/**
 * 用户 Converter
 *
 * @author yuelong.liang
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * 注册表单转实体类
     *
     * @param form form
     * @return User
     */
    User toEntity(RegisterForm form);

    /**
     * 实体类转 VO
     *
     * @param user 用户信息
     * @return UserinfoVO
     */
    UserinfoVO toVO(User user);

}
