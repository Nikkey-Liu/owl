package cn.wan.owl.dto;

import lombok.Data;
import lombok.experimental.var;
import org.apache.shiro.authc.UsernamePasswordToken;


@Data
public class UserLoginDto {

    private String username;

    private String password;
    private String userlevel;
    public  String getUserlevel(){
        return this.userlevel;
    }
    public UsernamePasswordToken getUserPasswordToken() {
        return new UsernamePasswordToken(username, password);
    }
}
