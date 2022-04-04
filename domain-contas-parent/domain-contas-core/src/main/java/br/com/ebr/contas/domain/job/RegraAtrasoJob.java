package br.com.ebr.contas.domain.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.ebr.contas.domain.service.RegraAtrasoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegraAtrasoJob {
	
	@Autowired
	private RegraAtrasoService regraAtrasoService;	
	
	@Scheduled(cron = "${jobs.evict-cache.regra-atraso}")
	public void evictCache() {
		log.info("Limpando o cache da Regra de Atraso do Pagamento...");
		regraAtrasoService.evictCacheRegraAtraso();
	}

}
