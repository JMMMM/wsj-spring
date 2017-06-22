package com.wsj.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * Created by Jimmy on 2017/6/22.
 */
@Configuration
@ImportResource(locations = {"classpath:applications.xml"})
public class XmlConfigs{
}
