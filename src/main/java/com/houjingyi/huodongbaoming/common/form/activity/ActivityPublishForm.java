package com.houjingyi.huodongbaoming.common.form.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 活动发布表单
 *
 * @author yuelong.liang
 */
@Data
public class ActivityPublishForm {

    @NotBlank(message = "活动标题不能为空")
    @Length(message = "标题过长, 请修改", max = 31)
    private String title;

    @NotBlank(message = "请输入活动描述")
    @Length(message = "活动描述过长，请修改", max = 255)
    private String content;

    @NotNull(message = "请输入活动人数")
    @Min(message = "活动人数不能小于 3 人", value = 3)
    private Integer limitNum;

    @NotBlank(message = "活动地区编码不能为空")
    private String acAreaCode;

    @NotBlank(message = "活动地区名称不能为空")
    private String acAreaName;

    @NotBlank(message = "详细地址不能为空")
    private String address;

    @NotNull(message = "请选择活动日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

}
