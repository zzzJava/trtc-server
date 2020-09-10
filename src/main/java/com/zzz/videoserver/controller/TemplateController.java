package com.zzz.videoserver.controller;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractModel;
import com.zzz.videoserver.model.common.Msg;
import com.zzz.videoserver.model.common.ResultData;
import com.zzz.videoserver.model.params.impl.BasicParam;
import com.zzz.videoserver.model.params.impl.TranscodeTemplateParam;
import com.zzz.videoserver.service.impl.TranscodeTemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/template")
public class TemplateController {
    @Autowired
    private TranscodeTemplateServiceImpl transcode;

    @PostMapping(path = "transcodeTemplate")
    public ResultData<String> createTranscodeTemplate(@RequestParam(name = "name") String name,
                                                      @RequestParam(name = "container", defaultValue = "mp4") String container) {
        TranscodeTemplateParam param = new TranscodeTemplateParam(name, container);
        AbstractModel resp = transcode.createTemplate(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }

    @DeleteMapping(path = "transcodeTemplate")
    public ResultData<String> deleteTranscodeTemplate() {
        return null;
    }

    @PutMapping(path = "transcodeTemplate")
    public ResultData<String> modifyTranscodeTemplate() {
        return null;
    }

    @GetMapping(path = "transcodeTemplate")
    public ResultData<String> getTranscodeTemplates(@RequestParam(name = "offset", defaultValue = "0")Long offset,
                                                    @RequestParam(name = "limit", defaultValue = "10")Long limit) {
        BasicParam param = new BasicParam();
        param.setLimit(limit);
        param.setOffset(offset);
        AbstractModel resp = transcode.getAllTemplates(param);
        if (resp == null) {
            return new ResultData<>(Msg.ERROR, "腾讯云请求异常");
        }
        return new ResultData<>(JSON.toJSONString(resp));
    }


}
