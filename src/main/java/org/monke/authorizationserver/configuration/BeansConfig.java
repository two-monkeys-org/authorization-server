package org.monke.authorizationserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final MappingMongoConverter mappingMongoConverter;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void setUpMongoEscapeCharacterConversion() {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
