package cn.yijicai.app.handle;

import cn.yijicai.app.handlemapping.RequestContext;
import cn.yijicai.app.handlemapping.RequestHandle;
import cn.yijicai.app.model.User;
import cn.yijicai.app.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by sujian on 2016/4/8.
 */
public class QueryUserHandle implements RequestHandle{

    @Resource
    private UserServiceImpl userService;

    @Override
    public Object dealWithData(Map<String, Object> data) {
        Map<String,Object> params= (Map<String, Object>) data.get(RequestContext.PARAMS);
        String username= (String) params.get("username");
        List<User> users=userService.selectSelective(new User().setUsername(username));
        return users==null?Boolean.TRUE:users;
    }
}
