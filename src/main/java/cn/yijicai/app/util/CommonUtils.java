package cn.yijicai.app.util;

import cn.yijicai.app.handlemapping.RequestContext;

import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public class CommonUtils{
    /**
     * 检查处理之后是否是字符串
     * @param data
     * @return String
     */
    public static String checkResult(Map<String,Object> data){
        Object result=data.get(RequestContext.RESULT);
        String ret;
        if (result!=null && !(result instanceof String)){
            ret=GsonUtils.create().toJson(result);
        }
        else if (result==null){
            ret="{}";
        }
        else{
            ret=(String)result;
        }
        return ret;
    }
}
