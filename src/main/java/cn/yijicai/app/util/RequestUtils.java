package cn.yijicai.app.util;

import cn.yijicai.app.handlemapping.RequestContext;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public class RequestUtils{

    private final static Logger logger=Logger.getLogger(RequestUtils.class);

    /**
     * post body数据解析接口
     */
    public interface PostParam{
        Map<String,Object> param(HttpServletRequest request);
    }

    /**
     * 默认body内容解析实例
     */
    public final static PostParam DEFAULT_POST_PARAM=new PostParam() {
        @Override
        public Map<String, Object> param(HttpServletRequest request) {
            return null;
        }
    };

    /**
     * 获取请求方法
     * @param request 请求
     * @return String
     */
    public static String method(HttpServletRequest request){
        return request.getMethod();
    }

    /**
     * 获取get请求方法参数
     * @param request 请求
     * @return Map
     */
    public static Map<String,Object> getParams(HttpServletRequest request){
        Map<String,Object> params=new HashMap();
        Enumeration<String> enumeration=request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String key=enumeration.nextElement();
            params.put(key,request.getParameter(key));
        }
        return params;
    }
    /**
     * 获取post请求方法参数
     * @param request 请求
     * @return Map
     */
    public static Map<String,Object> postParams(HttpServletRequest request){
        Map<String,Object> params=getParams(request);
        try {
            //获取body中的数据
            InputStream is=request.getInputStream();
            StringBuffer buffer=new StringBuffer();
            int maxLen=1024;
            byte[] data=new byte[maxLen];
            int len;
            while ((len=is.read(data,0,maxLen))>0){
                buffer.append(new String(data,0,len));
            }
            if (buffer.length()>0){
                params.put(RequestContext.BODYPARAMS,buffer.toString());
            }
        } catch (IOException e) {
            logger.error("postParams",e);
        }
        return params;
    }
}
