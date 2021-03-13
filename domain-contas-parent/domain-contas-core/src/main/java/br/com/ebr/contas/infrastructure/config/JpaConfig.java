package br.com.ebr.contas.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = {"br.com.ebr.contas.domain.entity", "org.springframework.data.jpa.convert.threeten"})
@EnableJpaRepositories(basePackages = "br.com.ebr.contas.domain.repository")
public class JpaConfig { 
	
}
