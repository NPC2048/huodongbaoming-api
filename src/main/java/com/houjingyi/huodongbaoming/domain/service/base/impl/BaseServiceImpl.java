package com.houjingyi.huodongbaoming.domain.service.base.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseEntity;
import com.houjingyi.huodongbaoming.domain.service.base.BaseService;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 基础 Service 实现
 *
 * @param <M> Mapper
 * @param <T> T
 * @author yuelong.liang
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public boolean save(T entity) {
        resolveEntity(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        resolveEntity(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        resolveEntity(entityList);
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        resolveEntity(entityList);
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        resolveEntity(entity);
        return super.saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        resolveEntity(entityList);
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        resolveEntity(entityList);
        return super.updateBatchById(entityList, batchSize);
    }

    /**
     * 处理实体类
     *
     * @param entityList 实体类列表
     */
    public void resolveEntity(Collection<T> entityList) {
        if (entityList != null) {
            for (T entity : entityList) {
                resolveEntity(entity);
            }
        }
    }

    /**
     * 处理实体类
     *
     * @param entity entity
     */
    public void resolveEntity(T entity) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = StpUtil.getLoginIdAsLong();
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(now);
            entity.setCreateUser(userId);
        }
        entity.setUpdateTime(now);
        entity.setUpdateUser(userId);
    }

    @Override
    public boolean exists(Long id) {
        return SqlHelper.retCount(baseMapper.selectCount(
                Wrappers.<T>lambdaQuery().eq(T::getId, id)
        )) > 0;
    }
}
