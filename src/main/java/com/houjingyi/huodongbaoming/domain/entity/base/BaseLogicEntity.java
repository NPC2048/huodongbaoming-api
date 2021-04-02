package com.houjingyi.huodongbaoming.domain.entity.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 逻辑删除基础类
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseLogicEntity extends BaseEntity {

    @TableLogic(delval = "1")
    private Byte status;

}
