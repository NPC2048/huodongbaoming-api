package com.houjingyi.huodongbaoming.domain.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseEntity;

/**
 * 基础 Service
 *
 * @author yuelong.liang
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

    /**
     * 判断当前 id 是否存在
     *
     * @param id id
     * @return bool
     */
    boolean exists(Long id);

}
