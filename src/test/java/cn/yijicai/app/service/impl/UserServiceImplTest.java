package cn.yijicai.app.service.impl;

import cn.yijicai.app.BaseTest;
import cn.yijicai.app.model.User;
import cn.yijicai.app.util.GsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by sujian on 2016/4/8.
 */
public class UserServiceImplTest extends BaseTest {

    @Resource
    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(userService);
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        /*int len=3;
        String[][][] arr=new String[len][len][len];
        for (int iter_x=0;iter_x<len;iter_x++){
            for (int iter_y=0;iter_y<len;iter_y++){
                for (int iter_z=0;iter_z<len;iter_z++){
                    arr[iter_x][iter_y][iter_z]=iter_x+"-"+iter_y+iter_z;
                }
            }
        }
        System.out.println(GsonUtils.create().toJson(arr).toString());*/
    }

    @Test
    public void insertSelective() throws Exception {
        int f=userService.insertSelective(new User().setNickname("123")
                .setUsername("\ue420")
                .setPassword("\ue058"));
        Assert.assertTrue(f>0);
    }

    @Test
    public void selectSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        User user=userService.selectByPrimaryKey(1L);
        Assert.assertNotNull(user);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }
}