package com.bista.productservice.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "product")
@Data
public class PropertiesConfig {

	private Map<String, String> datasource;
}
