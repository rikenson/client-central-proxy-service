package org.senbyoz.proxy.common.config;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class WebfluxConfig {
    @Primary
    @Bean(name = "webFluxProps")
    public WebFluxProperties webFluxProperties(){
        return new WebFluxProperties();
    }
}
