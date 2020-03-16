package cn.wan.owl.service;

import cn.wan.owl.mapper.UserMapper;
import cn.wan.owl.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wanhongji
 * @Date: 2020/2/8
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryByUserId(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User queryByUserAccount(String userAccount) {
        return userMapper.selectByUserAccount(userAccount);
    }

    public void insert(User record) {
        if (record == null) {
            return;
        }
        userMapper.insertSelective(record);
    }

    public void update(User record) {
        if (record == null) {
            return;
        }
        userMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
