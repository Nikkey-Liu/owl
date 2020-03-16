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

        //基本连接信息
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());

        //具体配置
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(0);
        dataSource.setMaxActive(1);

        //空闲检测
        dataSource.setTestWhileIdle(true);
        //调用连接时检测是否可用
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1");
        //获取连接的等待超时时间
        dataSource.setMaxWait(1000 * 20L);
        //检测需要关闭的空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 30L);
        //连接池的最小生存时间
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
