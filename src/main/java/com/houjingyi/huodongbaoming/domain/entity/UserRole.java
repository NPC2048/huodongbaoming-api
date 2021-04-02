package com.houjingyi.huodongbaoming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseIdEntity;
import lombok.*;

/**
 * 用户角色表
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hd_user_role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends BaseIdEntity {

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 角色 id
     */
    private Long roleId;

}
