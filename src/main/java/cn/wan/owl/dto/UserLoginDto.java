package cn.wan.owl.dto;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class UserLoginDto {

    private String account;

    private String password;

    public UsernamePasswordToken getUserPasswordToken() {
        return new UsernamePasswordToken(account, password);
    }
}
