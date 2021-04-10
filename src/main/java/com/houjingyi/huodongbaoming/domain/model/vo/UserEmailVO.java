package com.houjingyi.huodongbaoming.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回用户名与邮箱
 *
 * @author yuelong.liang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailVO {

    private Long id;

    private String name;

    private String email;

}
