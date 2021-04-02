package com.houjingyi.huodongbaoming.domain.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 基础 id 实体类
 *
 * @author yuelong.liang
 */
@Data
public class BaseIdEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

}
