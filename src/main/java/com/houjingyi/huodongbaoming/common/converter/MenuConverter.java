package com.houjingyi.huodongbaoming.common.converter;

import com.houjingyi.huodongbaoming.domain.entity.Menu;
import com.houjingyi.huodongbaoming.domain.model.vo.MenuVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 菜单 Converter
 *
 * @author yuelong.liang
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    /**
     * 实体类转 VO
     *
     * @param entity 实体类
     * @return VO
     */
    MenuVO toVO(Menu entity);

    /**
     * 实体类集合转 VO
     *
     * @param entityList 实体类集合
     * @return List
     */
    List<MenuVO> toVOList(List<Menu> entityList);

}
