package com.zzz.videoserver.model.params.impl;

import com.zzz.videoserver.model.params.TrtcParams;
import lombok.Data;

/**
 * 腾讯云不少接口只需要少数几个基本类型参数，
 * 本类封装这些常用的基本类型参数方便使用
 */
@Data
public class BasicParam extends TrtcParams {
    /**
     * 房间号
     */
    private Integer RoomId;
    /**
     * 应用id
     */
    private Long SdkAppId;
    /**
     * 单个操作时的用户id
     */
    private String UserId;
    /**
     * 批量操作时的用户id
     */
    private String[] UserIds;
    /**
     * 模板查询起点
     */
    private Long offset;
    /**
     * 模板查询数量
     */
    private Long limit;
}
