package com.houjingyi.huodongbaoming.domain.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author yuelong.liang
 */
@Data
public class MenuVO implements IVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
    private String sequence;

}
