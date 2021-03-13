package br.com.ebr.contas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import br.com.ebr.contas.infrastructure.config.CachingConfig;
import br.com.ebr.contas.infrastructure.config.JpaConfig;
import br.com.ebr.contas.infrastructure.config.ScheduleConfig;
import br.com.ebr.contas.infrastructure.config.WebConfig;

@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = "classpath:domain-contas-core/src/main/resources/application.properties")
@Import({ WebConfig.class, CachingConfig.class, JpaConfig.class, ScheduleConfig.class })
public class ContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasApplication.class, args);
	}

}
