package cn.wan.owl.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class DataSourceConfig {

    private String url = "jdbc:mysql://localhost:3306/myowldatabase";

    private String username = "root";

    private String password = "123456";

}