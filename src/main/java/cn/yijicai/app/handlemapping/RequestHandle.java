package cn.yijicai.app.handlemapping;

import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public interface RequestHandle{
    /**
     * map内容处理
     * @param data
     * @return Object
     */
    Object dealWithData(Map<String,Object> data);
}
