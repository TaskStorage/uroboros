package com.taskstorage.uroboros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.taskstorage.uroboros" })
public class DbConfig {

        @Autowired
        private Environment environment;

        @Bean
        public LocalSessionFactoryBean sessionFactory() {
                LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
                sessionFactory.setDataSource(dataSource());
                sessionFactory.setPackagesToScan(new String[] { "com.taskstorage.uroboros.model"});
                sessionFactory.setHibernateProperties(hibernateProperties());
                return sessionFactory;
        }

        @Bean
        public DataSource dataSource() {
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
                dataSource.setUrl(environment.getRequiredProperty("datasource.url"));
                dataSource.setUsername(environment.getRequiredProperty("datasource.username"));
                dataSource.setPassword(environment.getRequiredProperty("datasource.password"));
                return dataSource;
        }

        private Properties hibernateProperties() {
                Properties properties = new Properties();
                properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
                properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
                properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
                properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
                //Caching properties
                properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
                properties.put("hibernate.cache.use_second_level_cache","true");
                properties.put("net.sf.ehcache.configurationResourceName", "ehcache.xml");
                return properties;
        }

        //Вместо вызовов session.openTransaction() и session.commit() используется аннотация @Transactional
        @Bean
        public HibernateTransactionManager getTransactionManager() {
                HibernateTransactionManager transactionManager = new HibernateTransactionManager();
                transactionManager.setSessionFactory(sessionFactory().getObject());
                return transactionManager;
        }
}
