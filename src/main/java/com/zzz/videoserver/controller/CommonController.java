package com.zzz.videoserver.controller;

import com.zzz.videoserver.model.dto.SigDTO;
import com.zzz.videoserver.utils.SignUtil;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/common")
@Api(tags = {"通用API"})
public class CommonController {
    @Autowired
    private SignUtil signUtil;
    @GetMapping(path = "sign")
    @ApiOperation(value = "获取用户签名", notes = "客户端通过此接口获取用户签名，然后可以访问腾讯云")
    @ApiImplicitParam(name = "userId", value = "需要访问tencent的客户端用户名")
    public SigDTO getSignature(@RequestParam(name = "userId", required = true) String userId) {
        SigDTO dto = new SigDTO();
        dto.setMode("rtc");
        dto.setSdkAppId(signUtil.getSdkappid());
        dto.setUserId(userId);
        try {
            String userSig =  signUtil.genSig(userId, 24*60*60);
            dto.setUserSig(userSig);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
