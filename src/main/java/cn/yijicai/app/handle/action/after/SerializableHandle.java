package cn.yijicai.app.handle.action.after;

import cn.yijicai.app.handlemapping.RequestHandle;
import cn.yijicai.app.util.CommonUtils;

import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public class SerializableHandle implements RequestHandle{
    @Override
    public Object dealWithData(Map<String, Object> data){
        return CommonUtils.checkResult(data);
    }
}
