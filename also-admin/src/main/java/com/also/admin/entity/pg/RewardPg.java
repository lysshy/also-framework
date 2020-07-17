package com.also.admin.entity.pg;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@TableName("t_sys_reward")
public class RewardPg {

    private Integer id;

    private Integer reportId;

    private Integer rewardAmount;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

    private Integer modifierId;

    private String reason;

    private Date rewardTime;

    private Integer actualAmount;

    private Integer rewardUserId;

    private String payMerchantNo;

    private String wxPayStatus;

    private String rewardOpenid;

    private String merchantBillNo;

    private String remark;

    private Date refundTime;

    private Integer refundAmount;

    private Date recTime;

}
