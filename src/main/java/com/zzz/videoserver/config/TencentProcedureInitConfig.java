package com.zzz.videoserver.config;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.vod.v20180717.models.AdaptiveDynamicStreamingTaskInput;
import com.tencentcloudapi.vod.v20180717.models.DescribeProcedureTemplatesResponse;
import com.tencentcloudapi.vod.v20180717.models.ProcedureTemplate;
import com.zzz.videoserver.config.properties.VodProperties;
import com.zzz.videoserver.model.params.TrtcParamsAdapter;
import com.zzz.videoserver.model.params.impl.AdaptiveTranscodeParam;
import com.zzz.videoserver.service.TaskTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化类，在application初始化完成后执行
 * 检查腾讯云点播应用中是否存在 tencent.vod.adaptive-transcode-task对应的任务流
 * 如果不存在，则创建一个自适应码流任务流
 */
@Configuration
@EnableConfigurationProperties({VodProperties.class})
@Slf4j
public class TencentProcedureInitConfig implements ApplicationRunner {
    @Autowired
    private TaskTemplateService taskService;
    @Autowired
    VodProperties vodProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String taskName = vodProperties.getAdaptiveTranscodeTask();
        String[] names = {taskName};
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("Names", names);
        // 构造参数
        TrtcParamsAdapter params = new TrtcParamsAdapter(paramMap);
        DescribeProcedureTemplatesResponse resp =
                (DescribeProcedureTemplatesResponse)taskService.getTasksTemplate(params);
        ProcedureTemplate[] procedureTemplateSet = resp.getProcedureTemplateSet();
        if (procedureTemplateSet.length == 0) { // 没有对应的自适应码流任务流，创建新的
            createAdaptiveTranscodeProcedure(taskName);
        }
    }

    private void createAdaptiveTranscodeProcedure(String name) {
        AdaptiveTranscodeParam param = new AdaptiveTranscodeParam(name);
        AdaptiveDynamicStreamingTaskInput task = new AdaptiveDynamicStreamingTaskInput();
        task.setDefinition(10l);
        AdaptiveDynamicStreamingTaskInput[] inputs = {task};
        param.setAdaptiveDynamicStreamingTaskSet(inputs);
        AbstractModel taskTemplate = taskService.createTaskTemplate(param);
        if (taskTemplate == null) {
            throw new RuntimeException("请求腾讯云api失败，请检查网络或者参数配置");
        }
        log.info("创建转自适应码流任务流成功");
    }
}
