package com.houjingyi.huodongbaoming.common.form.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础查询表单
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseQueryForm<T> extends Page<T> implements IPage<T> {

}
