package com.also.framework.security.domain.auth;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("sys_user_role")
public class BaseUserRole {

    private Long id;

    private Long userId;

    private Long roleId;
}
