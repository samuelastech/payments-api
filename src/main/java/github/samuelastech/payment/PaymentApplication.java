package github.samuelastech.payment;

import github.samuelastech.config.Config;
import github.samuelastech.order.OrderClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(Config.class)
public class PaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}
