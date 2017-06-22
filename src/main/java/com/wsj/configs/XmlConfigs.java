package com.wsj.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Jimmy on 2017/6/22.
 */
@Configuration
@ImportResource(locations={"classpath:applications.xml"})
public class XmlConfigs {
}
