package yxinfo.yjh.web.context;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by dy on 2017/9/15.
 */
public class JsonContext {

    /**
     * Json配置
     */
    public static final SerializerFeature[] SERIALIZER_FEATURES = {
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullListAsEmpty
    };
}
