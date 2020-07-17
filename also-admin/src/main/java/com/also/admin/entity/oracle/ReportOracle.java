package com.also.admin.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@TableName("t_sys_report")
public class ReportOracle extends SyncEntity {

    @TableId
    private String id;

    private String type;

    private String imgUrl;

    private String videoUrl;

    private Date illegalTime;

    private String location;

    private String carType;

    private String carNum;

    private String source;

    private String reportNum;

    private String queryCode;

    private String gpsx;

    private String gpsy;

    private String gpsh;

    private Integer districtId;

    private String description;

    private Integer status;

    private String failureReason;

    private Integer isEncrypt;

    private Integer isRepeat;

    private Integer isUpload;

    private Date synchronizeTime;

    private int evidenceType;

    private Integer dbStatus;

    private Date createTime;

    private Integer creatorId;

    private Date modifyTime;

    private Integer modifierId;

    private Integer workFlowStatus;

    private String sixLocation;

    private String sixIllegalType;

    private String enforceNum;

    private String remark;

    private Integer appUserId;

    private Integer sync;

    private String carColor;

}
