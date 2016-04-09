package cn.yijicai.app.controller;

import cn.yijicai.app.handlemapping.RequestContext;
import cn.yijicai.app.handlemapping.RequestSwitch;
import cn.yijicai.app.util.CommonUtils;
import cn.yijicai.app.util.RequestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * set方法支持事务
 * get方法不支持
 * Created by sujian on 2016/4/7.
 */

@Controller
public class YijicaiDataController{

    private final static Logger logger=Logger.getLogger(YijicaiDataController.class);
    private final static String errMsg="{}";

    @Resource
    private RequestSwitch actionSwitch;

    @RequestMapping("/set")
    public ModelAndView update(HttpServletRequest request){
        try{
            return safeUpdate(request);
        }
        catch (Exception ex){
            logger.error("update",ex);
        }
        return new ModelAndView("msg","msg",errMsg);
    }

    @RequestMapping("/get")
    public ModelAndView query(HttpServletRequest request){
        try {
            return safeQuery(request);
        }
        catch (Exception ex){
            logger.error("query",ex);
        }
        return new ModelAndView("msg","msg",errMsg);
    }

    private String safeDeal(HttpServletRequest request){
        Map<String,Object> requestMap= new HashMap();
        //获取请求方法
        requestMap.put(RequestContext.METHOD, RequestUtils.method(request));
        //根据请求方法获取参数
        if ("get".equalsIgnoreCase((String) requestMap.get(RequestContext.METHOD))){
            requestMap.put(RequestContext.PARAMS,
                    RequestUtils.getParams(request));
        }

        actionSwitch.deal(requestMap);//处理

        //结果处理（异常处理）
        return CommonUtils.checkResult(requestMap);
    }

    private ModelAndView safeQuery(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg",safeDeal(request));
        mv.setViewName("msg");
        return mv;
    }

    @Transactional
    private ModelAndView safeUpdate(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg",safeDeal(request));
        mv.setViewName("msg");
        return mv;
    }
}