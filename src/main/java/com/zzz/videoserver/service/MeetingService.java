package com.zzz.videoserver.service;

import com.tencentcloudapi.common.AbstractModel;
import com.zzz.videoserver.model.params.TrtcParams;

/**
 * 会议相关操作，相关操作如下：
 * 1、开启/停止混流录制
 * 2、房间用户管理
 * 3、解散房间
 */
public interface MeetingService {

    AbstractModel startMix(TrtcParams trtcParams);

    AbstractModel stopMix(TrtcParams trtcParams);

    AbstractModel removeUsers(TrtcParams trtcParams);

    AbstractModel dismissRoom(TrtcParams trtcParams);
}
