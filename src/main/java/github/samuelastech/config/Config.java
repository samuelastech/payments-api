package github.samuelastech.config;

import github.samuelastech.order.OrderClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "github.samuelastech")
@EnableAutoConfiguration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
