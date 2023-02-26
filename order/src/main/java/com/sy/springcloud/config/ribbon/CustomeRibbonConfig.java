package com.sy.springcloud.config.ribbon;


import com.sy.ribbon.GlobalRibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = GlobalRibbonConfig.class)
public class CustomeRibbonConfig {
}
