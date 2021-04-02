package com.houjingyi.huodongbaoming.domain.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.houjingyi.huodongbaoming.domain.model.vo.IVO;
import com.houjingyi.huodongbaoming.domain.service.base.BaseVoService;

/**
 * 基础 VO service 实现
 * @author yuelong.liang
 */
public class BaseVoServiceImpl<T, VO extends IVO> implements BaseVoService<T, VO> {
    @Override
    public VO page(IPage<T> page, Wrapper<T> wrapper) {
        return null;
    }

    @Override
    public VO view(Long id) {
        return null;
    }
}
