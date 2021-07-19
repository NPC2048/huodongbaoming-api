package com.houjingyi.huodongbaoming.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.common.converter.UserConverter;
import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.form.user.ModifyPassForm;
import com.houjingyi.huodongbaoming.common.form.user.ModifyUserinfoForm;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.common.result.Results;
import com.houjingyi.huodongbaoming.config.GravatarConfig;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.model.vo.UserinfoVO;
import com.houjingyi.huodongbaoming.domain.service.RoleService;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 用户信息 controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserinfoController {

    private final UserService userService;

    private final RoleService roleService;

    private final UserConverter userConverter;

    private final GravatarConfig gravatarConfig;

    @GetMapping("/info")
    public R info() {
        User user = userService.getById(StpUtil.getLoginIdAsString());
        String emailMd5 = DigestUtils.md5DigestAsHex(user.getEmail().getBytes(StandardCharsets.UTF_8));
        UserinfoVO vo = userConverter.toVO(user);
        vo.setGratavar(gravatarConfig.getProxy() + emailMd5 + "?s=64&d=identicon");
        return Results.success(vo);
    }

    @PostMapping("/modify-userinfo")
    public R submit(@RequestBody ModifyUserinfoForm form) {
        User user = userService.getById(StpUtil.getLoginIdAsString());
        List<String> roleList = roleService.listRoleNameByUserId(user.getId());
        if (GlobalConstants.ADMIN_NAME.equals(user.getName())
                && !GlobalConstants.ADMIN_NAME.equals(form.getName())
                && roleList.contains(GlobalConstants.ADMIN_ROLE)
        ) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "admin 账号不可更改名称");
        }
        // 用户名唯一检测
        if (userService.existsName(form.getName())) {
            return Results.failed("该用户名已存在: " + form.getName());
        }
        // 更新字段
        boolean result = userService.lambdaUpdate().set(User::getName, form.getName())
                .set(true, User::getGender, form.getGender())
                .ge(User::getId, user.getId()).update();
        return Results.state(result);
    }

    @PostMapping("/modify-pass")
    public R modifyPass(@RequestBody ModifyPassForm form) {
        // 校验密码输入
        if (!form.getNewPass().equals(form.getConfirmPass())) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "两次密码输入不一致");
        }
        if (form.getNewPass().equals(form.getOldPass())) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "新密码不能与旧密码相同");
        }
        User user = userService.getById(StpUtil.getLoginIdAsString());
        // 校验旧密码

        if (!user.getPassword().equals(DigestUtil.sha256Hex(form.getOldPass()))) {
            return Results.failed("旧密码错误");
        }
        // 修改密码
        user.setPassword(DigestUtil.sha256Hex(form.getNewPass()));
        this.userService.updateById(user);
        return Results.success();
    }

    /**
     * 获取邮箱的 avatar 链接
     *
     * @param email 邮箱
     * @return url
     */
    @GetMapping("/avatar-url")
    public R avatarUrl(@NotBlank(message = "邮箱不能为空") String email) {
        String emailMd5 = DigestUtils.md5DigestAsHex(email.getBytes(StandardCharsets.UTF_8));
        return Results.success(gravatarConfig.getProxy() + emailMd5 + "?s=64&d=identicon");
    }


}
