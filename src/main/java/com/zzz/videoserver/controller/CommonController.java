package com.zzz.videoserver.controller;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.trtc.v20190722.models.EncodeParams;
import com.zzz.videoserver.model.common.Msg;
import com.zzz.videoserver.model.common.ResultData;
import com.zzz.videoserver.model.dto.SigDTO;
import com.zzz.videoserver.model.params.impl.BasicParam;
import com.zzz.videoserver.model.params.impl.TransCodeParam;
import com.zzz.videoserver.service.MeetingService;
import com.zzz.videoserver.utils.RandomUtil;
import com.zzz.videoserver.utils.SignUtil;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(path = "api/common")
@Api(tags = {"通用API"})
public class CommonController {
    @Autowired
    private SignUtil signUtil;

    @Autowired
    private MeetingService meet;

    @GetMapping(path = "sign")
    @ApiOperation(value = "获取用户签名", notes = "客户端通过此接口获取用户签名，然后可以访问腾讯云")
    @ApiImplicitParam(name = "userId", value = "需要访问tencent的客户端用户名")
    public ResultData<SigDTO> getSignature(@RequestParam(name = "userId", required = true) String userId) {
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
        return new ResultData<>(dto);
    }

    @GetMapping(path = "roomId")
    @ApiOperation(value = "申请房间号", notes = "生成格式为32bit的整数房间号")
    public ResultData<Integer> getRoomId() {
        return new ResultData<>(RandomUtil.genRandomInt(6));
    }

    @GetMapping(path = "startMix")
    @ApiOperation(value = "开启混流录制", notes = "将房间中的音视频流混合到一起，并录制视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", required = true, dataType = "int", value = "房间号"),
            @ApiImplicitParam(name = "recordId", required = false, dataType = "string", value = "视频文件名称")
    })
    public ResultData<String> startMix(@RequestParam(name = "roomId")Integer roomId, @RequestParam(name = "recordId") String recordId) {
        TransCodeParam param = new TransCodeParam(roomId, recordId, signUtil.getSdkappid());
        AbstractModel resp = meet.startMix(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }

    @GetMapping(path = "stopMix")
    @ApiOperation(value = "停止混流录制", notes = "停止对房间音视频流的录制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", required = true, dataType = "int", value = "房间号")
    })
    public ResultData<String> stopMix(@RequestParam(name = "roomId") Integer roomId) {
        BasicParam param = newParam();
        param.setRoomId(roomId);
        AbstractModel resp = meet.stopMix(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }

    @GetMapping(path = "removeUsers")
    @ApiOperation(value = "移除用户", notes = "将用户从房间移除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", required = true, dataType = "int", value = "房间号")
    })
    public ResultData<String> removeUser(@RequestParam(name = "roomId")Integer roomId,
                                         @RequestParam(name = "users")String[] users) {
        BasicParam param = newParam();
        param.setRoomId(roomId);
        param.setUserIds(users);
        AbstractModel resp = meet.removeUsers(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }

    @GetMapping(path = "dismissRoom")
    @ApiOperation(value = "解散房间", notes = "解散房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", required = true, dataType = "int", value = "房间号")
    })
    public ResultData<String> dismissRoom(@RequestParam(name = "roomId") Integer roomId) {
        BasicParam param = newParam();
        param.setRoomId(roomId);
        AbstractModel resp = meet.dismissRoom(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }

    @GetMapping(path = "uploadSign")
    @ApiOperation(value = "获取上传签名", notes = "获取上传文件许可，有效期1天")
    public ResultData<String> uploadSignature(@RequestParam(name = "userId", required = true)String userId) throws Exception {
        return new ResultData<>(signUtil.getUploadSignature(userId));
    }

    private BasicParam newParam() {
        BasicParam param = new BasicParam();
        param.setSdkAppId(signUtil.getSdkappid());
        return param;
    }


}
