package com.abhi.crm.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.abhi.crm")
@PropertySource("classpath:dbValue.properties")
public class ConfigFile {
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//--DATA SOURCE CREATED
	@Bean
	public DataSource datasource() {
		ComboPooledDataSource cpd = new ComboPooledDataSource();
		try {
			cpd.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		logger.info("---->>Driver: " + env.getProperty("jdbc.driver"));
			cpd.setJdbcUrl(env.getProperty("jdbc.url"));
			cpd.setUser(env.getProperty("jdbc.user"));
			cpd.setPassword(env.getProperty("jdbc.password"));
		
		//--C3P0 PROPERTIES--
			
			cpd.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			cpd.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			cpd.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			cpd.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
			
		return cpd;
	}
	
	//--SESSION FACTORY CREATED
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionfactory = new LocalSessionFactoryBean();
		sessionfactory.setDataSource(datasource());
		sessionfactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionfactory.setHibernateProperties(hibernateProperties());
		
		return sessionfactory;
	}
	
	//--HIBERNATE PROPERTIES
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
}
