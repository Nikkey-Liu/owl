package cn.wan.owl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: wanhongji
 * @Date: 2020/2/7
 */

@SpringBootApplication
//@EnableScheduling
//@EnableConfigurationProperties
@MapperScan("cn.wan.owl.mapper")
public class OwlApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwlApplication.class, args);
    }
}
