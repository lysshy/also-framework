package com.also.framework.security.domain.auth;

import com.also.framework.security.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Setter
@Getter
@TableName("sys_user")
public class BaseUser extends BaseEntity {

    @NotEmpty(message = "user.name.empty")
    private String username;

    @NotEmpty(message = "user.pwd.empty")
    private String password;

}
