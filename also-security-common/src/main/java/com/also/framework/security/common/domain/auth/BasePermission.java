package com.also.framework.security.common.domain.auth;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_permission")
public class BasePermission {

    @TableId
    private Long id;

    private String name;

    private String code;

    private int type;

    private String url;

    private String methodType;

    private int sort;

    private Long parentId;
}
