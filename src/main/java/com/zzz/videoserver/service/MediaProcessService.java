package com.zzz.videoserver.service;

import com.tencentcloudapi.common.AbstractModel;
import com.zzz.videoserver.model.params.TrtcParams;

/**
 * 视频处理接口，
 */
public interface MediaProcessService {
    AbstractModel processByProcedure(TrtcParams param);
}
