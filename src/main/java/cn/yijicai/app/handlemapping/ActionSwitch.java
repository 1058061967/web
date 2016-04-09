package cn.yijicai.app.handlemapping;

import cn.yijicai.app.util.GsonUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * action处理控制器（根据参数中的action选择处理类）
 * Created by sujian on 2016/4/8.
 */
public class ActionSwitch implements RequestSwitch{

    private final static Logger logger=Logger.getLogger(ActionSwitch.class);

    /**
     * action-handleList
     */
    private Map<String,List<RequestHandle>> actions;
    /**
     * pre-action-handleList
     */
    private List<RequestHandle> preActions;
    /**
     * after-action-handleList
     */
    private List<RequestHandle> afterActions;


    private void preDeal(Map<String,Object> data){
        handleDeal(preActions,data);
    }

    private void afterDeal(Map<String,Object> data){
        handleDeal(afterActions,data);
    }

    private void handleDeal(List<RequestHandle> handles,Map<String,Object> data){
        for (RequestHandle handle :
                handles) {
            Object result=handle.dealWithData(data);
            if (result==null || result.equals(Boolean.FALSE)){
                break;
            }
            else if (result.equals(Boolean.TRUE)){
                continue;
            }
            else{
                data.put(RequestContext.RESULT,result);
            }
        }
    }

    /**
     * 根据参数中的action来查找处理类
     * @param data 请求参数封装
     */
    @Override
    public void deal(Map<String,Object> data){
        Map<String,Object> params= (Map<String, Object>) data.get(RequestContext.PARAMS);
        if (params.get(ServiceContext.ACTION)==null){
            logger.error("deal-noAction-"+GsonUtils.create().toJson(data));
            return;
        }
        String action= (String) params.get(ServiceContext.ACTION);
        List<RequestHandle> handles=actions.get(action);
        if (handles==null){
            logger.error("deal-noActionHandle-"+action);
            return;
        }
        preDeal(data);//请求之前统一处理
        handleDeal(handles,data);//处理请求
        afterDeal(data);//请求之后统一处理
    }

    public Map<String, List<RequestHandle>> getActions() {
        return actions;
    }

    public void setActions(Map<String, List<RequestHandle>> actions) {
        this.actions = actions;
    }

    public List<RequestHandle> getPreActions() {
        return preActions;
    }

    public void setPreActions(List<RequestHandle> preActions) {
        this.preActions = preActions;
    }

    public List<RequestHandle> getAfterActions() {
        return afterActions;
    }

    public void setAfterActions(List<RequestHandle> afterActions) {
        this.afterActions = afterActions;
    }
}
