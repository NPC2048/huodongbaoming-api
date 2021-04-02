package com.houjingyi.huodongbaoming.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.common.constant.RegexpConstants;
import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.form.AppValidForm;
import com.houjingyi.huodongbaoming.common.form.user.*;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.common.util.MailUtils;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.model.vo.MenuVO;
import com.houjingyi.huodongbaoming.domain.service.MenuService;
import com.houjingyi.huodongbaoming.domain.service.RoleService;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 Controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final MenuService menuService;

    /**
     * 邮箱 - 验证码 缓存 Map
     * 3 分钟有效
     */
    private static final TimedCache<String, String> EMAIL_CODE_CACHE = CacheUtil.newTimedCache(3 * 60 * 60 * 1000);

    static {
        EMAIL_CODE_CACHE.schedulePrune(500);
    }

    /**
     * 注册表单
     *
     * @param form 表单
     * @return R
     */
    @PostMapping("/register")
    public R register(@Valid @RequestBody RegisterForm form) {
        // 校验验证码
        String code = EMAIL_CODE_CACHE.get(form.getEmail(), false);
        if (code == null) {
            return R.failed("请先获取验证码");
        }
        if (!code.equals(form.getCode())) {
            return R.failed("验证码错误");
        }
        // 校验密码
        if (!form.getPassword().equals(form.getConfirmPass())) {
            return R.failed("两次密码校验不正确");
        }
        // 校验手机号是否唯一
        if (userService.existsEmail(form.getEmail())) {
            return R.failed("该邮箱已存在");
        }
        // 校验用户名
        if (userService.existsName(form.getName())) {
            return R.failed("该用户名已被注册");
        }
        // 保存用户
        boolean result = this.userService.register(form);
        if (result) {
            // 删除缓存
            EMAIL_CODE_CACHE.remove(form.getEmail());
        }
        return R.state(result);
    }

    /**
     * 获取邮件注册码
     *
     * @return R
     */
    @RequestMapping("/mail-code")
    public R getCode(@Valid @RequestBody MailCodeForm form) throws MessagingException {
        log.info("========== loginKey: " + StpUtil.getLoginKey() + ", sessionId: " + StpUtil.getTokenValue());
        String code = EMAIL_CODE_CACHE.get(form.getEmail(), false);
        if (code != null) {
            return R.failed("请勿重复获取验证码");
        }
        code = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        // 260697449@qq.com
        MailUtils.sendMime("活动报名-注册验证码", "您的验证码是：<span style='color: #66ccff'>" + code + "</span> <br> 3 分钟内有效", form.getEmail());
        EMAIL_CODE_CACHE.put(form.getEmail(), code);
        return R.SUCCESS;
    }

    /**
     * 登录
     *
     * @return R
     */
    @PostMapping("/login-email")
    public R login(@Valid @RequestBody LoginForm form) {
        User user = userService.loginByEmail(form);
        if (user == null) {
            return R.failed(ResultCodeEnum.INVALID_USER);
        }
        // 设置 login id
        StpUtil.setLoginId(user.getId(), "mobile");
        // 查询用户角色和菜单
        List<String> roleNameList = roleService.listRoleNameByUserId(user.getId());
        // 判断是否为管理员
        StpUtil.getTokenSession().setAttribute(GlobalConstants.SESSION_ROLE, roleNameList);
        // 查询菜单
        List<MenuVO> menuList = menuService.listMenuByLoginId();
        // 用户头像信息
        String emailMd5 = DigestUtils.md5DigestAsHex(user.getEmail().getBytes(StandardCharsets.UTF_8));
        String url = "https://study5.icanokok.com/avatar/" + emailMd5 + "?s=64&d=identicon";
        // 颁发 token
        Map<String, Object> map = new HashMap<>(4);
        map.put("menuList", menuList);
        map.put("tokenInfo", StpUtil.getTokenInfo());
        map.put("avatar", url);
        return R.success(map);
    }

    @PostMapping("/forget-code")
    public R forgetCode(@Valid @RequestBody AppValidForm<String> form) throws MessagingException {
        // 判断是否为邮箱格式
        if (!form.getData().matches(RegexpConstants.EMAIL)) {
            // 不为邮箱格式, 根据用户名查找邮箱
            User user = userService.lambdaQuery().select(User::getEmail).ge(User::getName, form.getData()).one();
            if (user == null) {
                return R.failed(ResultCodeEnum.VALID_ERR, "该用户名未注册");
            }
            form.setData(user.getEmail());
        }
        if (!userService.existsEmail(form.getData())) {
            return R.failed(ResultCodeEnum.VALID_ERR, "该邮箱未注册");
        }
        // 发送验证码
        String code = EMAIL_CODE_CACHE.get(form.getData(), false);
        if (code != null) {
            return R.failed("请勿重复获取验证码");
        }
        code = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        MailUtils.sendMime("活动报名-修改密码", "您正在修改密码，如果不是本人操作，请忽略。<br /> 您的验证码是：<span style='color: #66ccff'>" + code + "</span> <br> 3 分钟内有效", form.getData());
        EMAIL_CODE_CACHE.put(form.getData(), code);
        return R.SUCCESS;
    }

    @PostMapping("/modify-pass-by-email")
    public R modifyPassByEmail(@Valid @RequestBody ModifyPassByEmailForm form) {
        // 密码校验
        if (!form.getNewPass().equals(form.getConfirmPass())) {
            return R.failed("密码输入不一致，请检查");
        }
        User user;
        // 判断是否为邮箱格式
        if (!form.getName().matches(RegexpConstants.EMAIL)) {
            // 不为邮箱格式, 根据用户名查找邮箱
            user = userService.lambdaQuery().ge(User::getName, form.getName()).one();
            if (user == null) {
                return R.failed(ResultCodeEnum.VALID_ERR, "该用户名未注册");
            }
        } else {
            user = userService.lambdaQuery().eq(User::getEmail, form.getName()).one();
            if (user == null) {
                return R.failed(ResultCodeEnum.VALID_ERR, "该邮箱未注册");
            }
        }
        // 校验验证码
        String code = EMAIL_CODE_CACHE.get(user.getEmail(), false);
        if (code == null) {
            return R.failed(ResultCodeEnum.VALID_ERR, "请先获取验证码");
        }
        if (!code.equals(form.getCode())) {
            return R.failed(ResultCodeEnum.VALID_ERR, "验证码错误");
        }
        // 修改密码
        user.setPassword(DigestUtil.sha256Hex(form.getNewPass().getBytes(StandardCharsets.UTF_8)));
        userService.updateById(user);
        EMAIL_CODE_CACHE.remove(user.getEmail());
        return R.SUCCESS;
    }

    /**
     * 退出登录
     *
     * @return R
     */
    @PostMapping("/logout")
    public R logout() {
        log.info("========= logout: " + StpUtil.getLoginId());
        log.info("=========" + JSONUtil.toJsonStr(StpUtil.getTokenInfo()));
        return R.SUCCESS;
    }

    @PostMapping("/modify-mail-code")
    public R modifyMailCode(@Valid @RequestBody AppValidForm<String> form) throws MessagingException {
        // 判断与用户当前邮箱是否相同
        User user = userService.lambdaQuery().select(User::getId, User::getEmail).eq(User::getEmail, form.getData()).one();
        if (user != null) {
            // 判断用户 id 是否相同
            if (user.getId().equals(StpUtil.getLoginIdAsLong())) {
                return R.failed(ResultCodeEnum.VALID_ERR, "新邮箱不能与旧邮箱相同");
            } else {
                return R.failed(ResultCodeEnum.VALID_ERR, "该邮箱已被其他用户使用");
            }

        }
        // 发送验证码
        String code = EMAIL_CODE_CACHE.get(form.getData(), false);
        if (code != null) {
            return R.failed("请勿重复获取验证码");
        }
        code = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        // 260697449@qq.com
        MailUtils.sendMime("活动报名-修改头像/邮箱", "您的验证码是：<span style='color: #66ccff'>" + code + "</span> <br> 3 分钟内有效", form.getData());
        EMAIL_CODE_CACHE.put(form.getData(), code);
        return R.SUCCESS;
    }

    @PostMapping("/modify-email")
    public R modifyEmail(@Valid @RequestBody ModifyEmailForm form) {
        // 校验验证码
        String code = EMAIL_CODE_CACHE.get(form.getEmail(), false);
        if (code == null) {
            return R.failed(ResultCodeEnum.VALID_ERR, "请先获取验证码");
        }
        if (!code.equals(form.getCode())) {
            return R.failed(ResultCodeEnum.VALID_ERR, "验证码错误");
        }
        User user = userService.getById(StpUtil.getLoginIdAsString());
        if (user.getEmail().equals(form.getEmail())) {
            return R.failed(ResultCodeEnum.VALID_ERR, "新邮箱不能与旧邮箱相同");
        }
        if (userService.existsEmail(form.getEmail())) {
            return R.failed(ResultCodeEnum.VALID_ERR, "该邮箱已被其他用户使用");
        }
        user.setEmail(form.getEmail());
        this.userService.updateById(user);
        EMAIL_CODE_CACHE.remove(form.getEmail());
        return R.SUCCESS;
    }
}
