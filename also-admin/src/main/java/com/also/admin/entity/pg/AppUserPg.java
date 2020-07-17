package com.also.admin.entity.pg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@TableName("t_app_user")
public class AppUserPg {

    @TableId(type = IdType.INPUT)
    private Integer id;

    private String name;

    private String mobile;

    private String idCard;

    private Integer delStatus;

    private String pwd;

    private String salt;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    private Integer totalRewardAmount;

    private String unionid;

    private String wechatOpenid;

    private String wechatMiniOpenid;

    private String wechatMiniSessionKey;

}
