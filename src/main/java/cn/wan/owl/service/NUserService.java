package cn.wan.owl.service;

import cn.wan.owl.model.NUser;

public interface NUserService {
public NUser loginQueryUsername(String username);
public NUser queryUserId(int userid);
public void insertNuser(NUser nUser);
public void editNuser(NUser nUser);
public void deletNuser(int nuserid);
}
