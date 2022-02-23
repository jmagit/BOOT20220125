package com.example.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfiguracionJDBC {
//    @Bean
//    DataSource dataSource(Environment env) {
//    	DriverManagerDataSource ds = new DriverManagerDataSource();
//    	ds.setDriverClassName("com.mysql.jdbc.Driver");
//    	ds.setUrl("jdbc:mysql://localhost:3306/sakila");
//    	ds.setUsername("root");
//    	ds.setPassword("root");
//    	return ds;
//    }	
//
//    @Bean
//    JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);        
//    }
//     
//    @Bean
//    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    } 

}
