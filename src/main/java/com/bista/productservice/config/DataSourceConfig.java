package com.bista.productservice.config;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	@Autowired
	private PropertiesConfig propertiesConfig;

	@Bean
	public DataSource getDataSource() {
		
		
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		
		
		dataSourceBuilder.driverClassName(propertiesConfig.getDatasource().get("driverClassName"));
		dataSourceBuilder.url(propertiesConfig.getDatasource().get("url"));
		dataSourceBuilder.username(propertiesConfig.getDatasource().get("username"));
		dataSourceBuilder.password(propertiesConfig.getDatasource().get("password"));
		
		
		return dataSourceBuilder.build();
	}
}
