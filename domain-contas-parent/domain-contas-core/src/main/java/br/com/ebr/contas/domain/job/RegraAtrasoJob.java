package br.com.ebr.contas.domain.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.ebr.contas.domain.service.RegraAtrasoService;

public class RegraAtrasoJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegraAtrasoJob.class);
	
	@Autowired
	private RegraAtrasoService regraAtrasoService;	
	
	@Scheduled(cron = "${jobs.evict-cache.regra-atraso}")
	public void evictCache() {
		LOGGER.info("Limpando o cache da Regra de Atraso do Pagamento...");
		regraAtrasoService.evictCacheRegraAtraso();
	}

}
