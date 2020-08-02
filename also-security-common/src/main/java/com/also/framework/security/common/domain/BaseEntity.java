package com.also.framework.security.common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseEntity {

    @TableId
    private Long id;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createId;

    private Long updateId;

}
