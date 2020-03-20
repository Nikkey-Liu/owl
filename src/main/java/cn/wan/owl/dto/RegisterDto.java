package cn.wan.owl.dto;

import cn.wan.owl.model.NUser;
import lombok.Data;
import cn.wan.owl.util.Constantvalue;
import lombok.Data;

@Data
public class RegisterDto {

    private String username;

    private String password;
    private int userlevel;
    private String usertype;
    private String tel;
    private String address;
    public NUser getRegisterUserDto(){

        return new NUser( null,username,password,userlevel,usertype,tel,address);
    }
}
