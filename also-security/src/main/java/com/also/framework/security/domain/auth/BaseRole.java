package com.also.framework.security.domain.auth;

import com.also.framework.security.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@TableName("sys_role")
public class BaseRole extends BaseEntity {

    private String roleName;
}
