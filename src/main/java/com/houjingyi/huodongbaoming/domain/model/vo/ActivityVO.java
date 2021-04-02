package com.houjingyi.huodongbaoming.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houjingyi.huodongbaoming.common.constant.DateConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 活动 VO
 *
 * @author yuelong.liang
 */
@Data
public class ActivityVO implements IVO {

    private Long id;

    private String title;

    private String content;

    private String address;

    @JsonFormat(pattern = DateConstants.DEFAULT_DATE)
    private LocalDateTime date;

    private Byte status;

    private Integer limitNum;

    private Integer num;

    private String acAreaName;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 联系邮箱
     */
    private String email;
}
