package com.zzz.videoserver.model.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;

import java.util.Map;

/**
 * 将 Map 类型的参数适配为 TrtcParams类型，
 * 避免为了少量的请求参数而创建大量的请求参数类。
 * 比如：为了根据名称查找任务流，现在接口统一接收的参数类型
 * 都是TrtcParams，并且不想破坏这个规则，那我们的一种做法
 * 就是创建一个TrtcParams子类：
 * @Data
 * class SampleParam {
 *     String[] names;
 * }
 * 可以看见，上面的参数类只有一个属性，可能使用Map对象更简单一些。
 *
 * 现在提出Adapter，将普通 Map 对象适配为TrtcParams。
 */
public class TrtcParamsAdapter extends TrtcParams{
    private Map<String, Object> params;

    public TrtcParamsAdapter(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(params, new PascalNameFilter());
    }

}
