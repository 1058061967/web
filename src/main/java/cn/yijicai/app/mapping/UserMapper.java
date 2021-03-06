package cn.yijicai.app.mapping;

import cn.yijicai.app.model.User;
import org.springframework.stereotype.Component;

@Component("userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}