package com.esportslan.microservices.esportslanapi;

import com.treblle.spring.annotation.EnableTreblle;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableEncryptableProperties
@EnableScheduling
//@EnableTreblle
@OpenAPIDefinition(
		info = @Info(
				title = "ESportsLAN_APIs",
				description = "All the ESports LAN APIs are available here",
				version = "1.0.0",
				termsOfService = "TheJackFolio.com",
				contact = @Contact(
						name = "Jack",
						email = "jk19011999@gmail.com"
				),
				license = @License(
						name = "TheJackFolio",
						url = "TheJackFolio.com"
				)
		)
)
public class EsportsLanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsportsLanApplication.class, args);
	}

}
