package com.also.admin.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@TableName("t_app_user")
public class AppUserOracle extends SyncEntity {

    @TableId
    private String id;

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
