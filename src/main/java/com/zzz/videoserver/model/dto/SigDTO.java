package com.zzz.videoserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SigDTO {
    @ApiModelProperty(value = "应用id")
    private long sdkAppId;
    private String userId;
    private String mode = "trc";
    private String userSig;
}
