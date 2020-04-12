package cn.wan.owl.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.wan.owl.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class OwlDataSourceConfig {

    @Autowired
    private DataSourceConfig config;

    @Primary
    @Bean(name = "primaryDS")
    public DataSource primaryDataSource() {

        DruidDataSource dataSource = new DruidDataSource();

        //Basic connection information
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());

        //Concrete arrangement
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(0);
        dataSource.setMaxActive(1);

        //Idle detection
        dataSource.setTestWhileIdle(true);
        //Detect availability when calling connection
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1");
        //Waiting for connection timeout
        dataSource.setMaxWait(1000 * 20L);
        //Detect idle connections that need to be closed
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 30L);
        //Connection pool minimum survival time
        dataSource.setMinEvictableIdleTimeMillis(1000 * 30L);

        return dataSource;

    }

    @Primary
    @Bean(name = "primarySSF")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDS") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "primaryTM")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "primarySST")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySSF") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
