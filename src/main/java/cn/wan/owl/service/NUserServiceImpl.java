package cn.wan.owl.service;

import cn.wan.owl.mapper.NUserMapper;
import cn.wan.owl.model.NUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NUserServiceImpl implements NUserService {
    @Autowired
    NUserMapper nUserMapper;
    @Override
    public NUser loginQueryUsername(String username) {
        return nUserMapper.selectByUsernameAndUserlever(username);
    }

    @Override
    public NUser queryUserId(int userid) {
        System.out.println(userid);
        NUser nUser=nUserMapper.selectByPrimaryKey(userid);
        System.out.println(nUser);
        return nUser;
    }

    @Override
    public void insertNuser(NUser nUser) {
        nUserMapper.insertSelective(nUser);
    }

    @Override
    public void editNuser(NUser nUser) {
        nUserMapper.updateByPrimaryKeySelective(nUser);
    }

    @Override
    public void deletNuser(int nuserid) {
        nUserMapper.deleteByPrimaryKey(nuserid);
    }
}
