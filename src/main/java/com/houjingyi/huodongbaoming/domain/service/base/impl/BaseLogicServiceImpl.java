package com.houjingyi.huodongbaoming.domain.service.base.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseLogicEntity;
import com.houjingyi.huodongbaoming.domain.service.base.BaseLogicService;

/**
 * 基础逻辑删除 Service 实现
 *
 * @param <M>
 * @param <T>
 * @author yuelong.liang
 */
public class BaseLogicServiceImpl<M extends BaseMapper<T>, T extends BaseLogicEntity> extends BaseServiceImpl<M, T> implements BaseLogicService<T> {
}
