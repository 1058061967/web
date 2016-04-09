package cn.yijicai.app.service;


import java.util.List;

/**
 * Created by sujian on 2016/4/8.
 */
public interface BaseService<T>{
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
