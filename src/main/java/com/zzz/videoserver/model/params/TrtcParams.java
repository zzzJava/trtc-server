package com.zzz.videoserver.model.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.zzz.videoserver.utils.SignUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TrtcParams {
    public String toJSONString() {
        return JSON.toJSONString(this, new PascalNameFilter());
    }

}
