package cn.com.zhejiangart.config;

import cn.com.zhejiangart.config.SensitiveConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置类
 */
@Configuration
public class DataSourceConfig {
    
    /**
     * 配置数据源
     * @return 数据源
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://120.26.127.190:3306/art_zhejiangart_");
        dataSource.setUsername("art_zhejiangart_");
        dataSource.setPassword(SensitiveConfig.getDatabasePassword());
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}