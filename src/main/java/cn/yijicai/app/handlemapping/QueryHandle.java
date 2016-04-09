package cn.yijicai.app.handlemapping;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public abstract class QueryHandle implements RequestHandle {

    private final static Logger logger=Logger.getLogger(QueryHandle.class);

    @Override
    public Object dealWithData(Map<String, Object> data) {
        try{
            return queryWithData(data);
        }
        catch (Exception ex){
            logger.error("dealWithData",ex);
        }
        return Boolean.TRUE;
    }

    public abstract Object queryWithData(Map<String,Object> data);
}
