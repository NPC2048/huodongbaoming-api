package com.houjingyi.huodongbaoming.domain.manage;

/**
 * 活动管理
 *
 * @author yuelong.liang
 */
public interface ActivityManage {

    /**
     * 报名活动， 不抛出异常则为成功
     * - 使用当前登录用户与活动绑定至活动中间表
     *
     * @param id 活动 id
     */
    void signup(Long id);

    /**
     * 取消报名活动
     *
     * @param id 活动 id
     */
    void cancel(Long id);

    /**
     * 活动签到
     *
     * @param id 活动 id
     */
    void signIn(Long id);

}
