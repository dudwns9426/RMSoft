package com.rmsoft.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 데이터베이스 설정을 관리하는 클래스입니다.
 * 이 클래스는 HikariCP를 사용하여 데이터베이스와 연결하고 MyBatis 설정을 구성합니다.
 * 또한 트랜잭션 관리를 위한 빈도 포함되어 있습니다.
 * 
 * @author Jeon Youngjun
 */
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@Configuration
public class DBConfig {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * HikariCP 설정을 관리하는 빈을 생성합니다.
     * 
     * @return HikariCP 설정
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    /**
     * 데이터베이스와 연결을 관리하는 DataSource 빈을 생성합니다.
     * 
     * @return 데이터베이스 연결 DataSource
     */
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    /**
     * MyBatis SqlSessionFactory를 생성합니다.
     * 
     * @return MyBatis SqlSessionFactory
     * @throws Exception SqlSessionFactory 생성 중 발생하는 예외
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
        factoryBean.setTypeAliasesPackage("com.rmsoft.*");
        factoryBean.setConfiguration(mybatisConfg());
        return factoryBean.getObject();
    }

    /**
     * MyBatis SqlSessionTemplate을 생성합니다.
     * 
     * @return MyBatis SqlSessionTemplate
     * @throws Exception SqlSessionTemplate 생성 중 발생하는 예외
     */
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * MyBatis 설정을 관리하는 빈을 생성합니다.
     * 
     * @return MyBatis 설정
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfg() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * 트랜잭션을 관리하는 빈을 생성합니다.
     * 
     * @return 트랜잭션 관리자
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}