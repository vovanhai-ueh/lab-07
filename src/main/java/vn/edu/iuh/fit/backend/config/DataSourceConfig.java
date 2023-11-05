package vn.edu.iuh.fit.backend.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.session.JdbcSessionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;

import javax.sql.DataSource;
/*
* Configure the datasources
* spring.datasource: for database that save main data
* session.datasource: for session database
* */
@Configuration
@EnableConfigurationProperties(JdbcSessionProperties.class)
public class DataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    @Qualifier("dataSource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource(@Qualifier("dataSource") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties("session.datasource")
    @Qualifier("sessionDataSourceProperties")
    public DataSourceProperties sessionDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("sessionDataSource")
    @SpringSessionDataSource
    public DataSource sessionDataSource(@Qualifier("sessionDataSourceProperties") DataSourceProperties sessionDataSourceProperties) {
        return sessionDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
}