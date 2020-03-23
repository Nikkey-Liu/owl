package cn.wan.owl.mapper;

import cn.wan.owl.model.NUser;
import org.apache.ibatis.annotations.Param;

public interface NUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(NUser record);

    int insertSelective(NUser record);
    NUser selectByUsernameAndUserlever(String username);
    NUser selectByPrimaryKey(@Param("userid")Integer userid);

    int updateByPrimaryKeySelective(NUser record);

    int updateByPrimaryKey(NUser record);
}