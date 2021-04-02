package com.houjingyi.huodongbaoming.domain.model.vo;

import lombok.Data;

/**
 * 用户信息 VO
 *
 * @author yuelong.liang
 */
@Data
public class UserinfoVO {

    private String name;

    private Integer gender;

    private String email;

    /**
     * 头像 url
     */
    private String gratavar;

}
