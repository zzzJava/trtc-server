package com.zzz.videoserver.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class SigDTO {
    private long sdkAppId;
    private String userId;
    private String mode = "trc";
    private String userSig;
}
