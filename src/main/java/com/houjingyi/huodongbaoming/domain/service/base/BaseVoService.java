package com.houjingyi.huodongbaoming.domain.service.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.houjingyi.huodongbaoming.domain.model.vo.IVO;

/**
 * vo 操作相关 Service
 *
 * @author yuelong.liang
 */
public interface BaseVoService<T, VO extends IVO> {

    /**
     * 分页查询，返回 VO
     *
     * @param page    page
     * @param wrapper wrapper
     * @return VO
     */
    VO page(IPage<T> page, Wrapper<T> wrapper);

    /**
     * 根据 id 查询视图
     *
     * @param id id
     * @return VO
     */
    VO view(Long id);

}
