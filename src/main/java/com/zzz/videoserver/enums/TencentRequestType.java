package com.zzz.videoserver.enums;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.trtc.v20190722.models.*;
import com.tencentcloudapi.vod.v20180717.models.*;

public enum TencentRequestType {
    // 启动混流
    STRAT_MCU_MIX(StartMCUMixTranscodeRequest.class, StartMCUMixTranscodeResponse.class, "StartMCUMixTranscode"),
    // 结束混流
    STOP_MCU_MIX(StopMCUMixTranscodeRequest.class, StopMCUMixTranscodeResponse.class, "StopMCUMixTranscode"),
    // 从房间移除用户
    REMOVE_USER(RemoveUserRequest.class, RemoveUserResponse.class, "RemoveUser"),
    // 解散房间
    DISMISS_ROOM(DismissRoomRequest.class, DismissRoomResponse.class, "DismissRoom"),
    // 查询房间列表
    // 申请上传视频
    UPLOAD_APPLY(ApplyUploadRequest.class, ApplyUploadResponse.class, "ApplyUpload"),
    // 创建转码模板
    CREATE_TRANSCODE_TEMP(CreateTranscodeTemplateRequest.class, CreateTranscodeTemplateResponse.class, "CreateTranscodeTemplate"),
    // 删除转码模板
    DELETE_TRANSCODE_TEMP(DeleteTranscodeTemplateRequest.class, DeleteTranscodeTemplateResponse.class, "DeleteTranscodeTemplate"),
    // 获取转码模板
    DESCRIBE_TRANSCODE_TEMP(DescribeTranscodeTemplatesRequest.class, DescribeTranscodeTemplatesResponse.class, "DescribeTranscodeTemplates"),
    // 修改转码模板
    MODIFY_TRANSCODE_TEMP(ModifyTranscodeTemplateRequest.class, ModifyTranscodeTemplateResponse.class, "ModifyTranscodeTemplate"),
    // 创建水印模板
    CREATE_WATERMARK_TEMP(CreateWatermarkTemplateRequest.class, CreateWatermarkTemplateResponse.class, "CreateWatermarkTemplate"),
    // 删除水印模板
    DELETE_WATERMARK_TEMP(DeleteWatermarkTemplateRequest.class, DeleteWatermarkTemplateResponse.class, "DeleteWatermarkTemplate"),
    // 获取水印模板
    DESCRIBE_WATERMARK_TEMP(DescribeWatermarkTemplatesRequest.class, DescribeWatermarkTemplatesResponse.class, "DescribeWatermarkTemplates"),
    // 修改水印模板
    MODIFY_WATERMARK_TEMP(ModifyWatermarkTemplateRequest.class, ModifyWatermarkTemplateResponse.class, "ModifyWatermarkTemplate"),
    // 创建视频分析模板
    CREATE_AIANALYSIS_TEMP(CreateAIAnalysisTemplateRequest.class, CreateAIAnalysisTemplateResponse.class, "CreateAIAnalysisTemplate"),
    // 删除视频分析模板
    DELETE_AIANALYSIS_TEMP(DeleteAIAnalysisTemplateRequest.class, DeleteAIAnalysisTemplateResponse.class, "DeleteAIAnalysisTemplate"),
    // 获取视频分析模板
    DESCRIBE_AIANALYSIS_TEMP(DescribeAIAnalysisTemplatesRequest.class, DescribeAIAnalysisTemplatesResponse.class, "DescribeAIAnalysisTemplates"),
    // 修改视频分析模板
    MODIFY_AIANALYSIS_TEMP(ModifyAIAnalysisTemplateRequest.class, ModifyAIAnalysisTemplateResponse.class,"ModifyAIAnalysisTemplate"),
    // 创建视频识别模板
    CREATE_AIRECOGNITION_TEMP(CreateAIRecognitionTemplateRequest.class, CreateAIRecognitionTemplateResponse.class, "CreateAIRecognitionTemplate"),
    // 删除视频识别模板
    DELETE_AIRECOGNITION_TEMP(DeleteAIRecognitionTemplateRequest.class, DeleteAIRecognitionTemplateResponse.class, "DeleteAIRecognitionTemplate"),
    // 获取视频识别模板
    DESCRIBE_AIRECOGNITION_TEMP(DescribeAIRecognitionTemplatesRequest.class, DescribeAIRecognitionTemplatesResponse.class, "DescribeAIRecognitionTemplates"),
    // 修改视频识别模板
    MODIFY_AIRECOGNITION_TEMP(ModifyAIRecognitionTemplateRequest.class, ModifyAIRecognitionTemplateResponse.class,"ModifyAIRecognitionTemplate"),
    // 创建视频审核模板
    CREATE_CONTENTREVIEW_TEMP(CreateContentReviewTemplateRequest.class, CreateContentReviewTemplateResponse.class, "CreateContentReviewTemplate"),
    // 删除视频审核模板
    DELETE_CONTENTREVIEW_TEMP(DeleteContentReviewTemplateRequest.class, DeleteContentReviewTemplateResponse.class, "DeleteContentReviewTemplate"),
    // 获取视频审核模板
    DESCRIBE_CONTENTREVIEW_TEMP(DescribeContentReviewTemplatesRequest.class, DescribeContentReviewTemplatesResponse.class, "DescribeContentReviewTemplates"),
    // 修改视频审核模板
    MODIFY_CONTENTREVIEW_TEMP(ModifyContentReviewTemplateRequest.class, ModifyContentReviewTemplateResponse.class,"ModifyContentReviewTemplate"),
    CREATE_TASK_TEMP(CreateProcedureTemplateRequest.class, CreateProcedureTemplateResponse.class, "CreateProcedureTemplate"),
    DELETE_TASK_TEMP(DeleteProcedureTemplateRequest.class, DeleteProcedureTemplateResponse.class, "DeleteProcedureTemplate"),
    DESCRIBE_TASK_TEMP(DescribeProcedureTemplatesRequest.class, DescribeProcedureTemplatesResponse.class, "DescribeProcedureTemplates"),
    MEDIA_PROCESS_BY_PROCEDURE(ProcessMediaByProcedureRequest.class, ProcessMediaByProcedureResponse.class, "ProcessMediaByProcedure");


    private Class<? extends AbstractModel> request;
    private Class<? extends AbstractModel> response;
    private String method;

    TencentRequestType(Class<? extends AbstractModel> request,
                       Class<? extends AbstractModel> response,
                       String methodName) {
        this.request = request;
        this.response = response;
        this.method = methodName;
    }

    public Class<? extends AbstractModel> getRequest() {
        return request;
    }

    public Class<? extends AbstractModel> getResponse() {
        return response;
    }

    public String getMethod() {
        return method;
    }
}
