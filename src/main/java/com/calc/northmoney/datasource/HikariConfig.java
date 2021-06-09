package com.calc.northmoney.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * hikari 配置多数据源
 *
 */
@Configuration
public class HikariConfig
{
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(
    		@Qualifier("mainDataSource") DataSource  mainDataSource
    		,@Qualifier("mysqlDataSource")  DataSource mysqlDataSource
    		,@Qualifier("historyDataSource")  DataSource historyDataSource
    		)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.INFO_SQLSERVER.name(), mainDataSource);
        targetDataSources.put(DataSourceType.INFO_MYSQL.name(), mysqlDataSource);
        targetDataSources.put(DataSourceType.INFO_SQLSERVER_HISTORY.name(), historyDataSource);
        return new DynamicDataSource(mysqlDataSource, targetDataSources);
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    public DataSource mainDataSource()
    {
    	return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource()
    {
    	return new HikariDataSource();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.history")
    public DataSource historyDataSource()
    {
    	return new HikariDataSource();
    }
    
    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
