package org.senbyoz.proxy.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "api-url")
public class ApiUrlConfig {
    private String mcpBaseUrl;
    private String customerBaseUrl;
    private String accountBaseUrl;
}
