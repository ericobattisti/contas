package br.com.ebr.contas.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.ebr.contas.domain.entity.ContaPagar;
import br.com.ebr.contas.domain.entity.RegraAtraso;
import br.com.ebr.contas.domain.repository.RegraAtrasoRepository;

@Service
public class RegraAtrasoService {
	
	@Autowired
	private RegraAtrasoRepository regraAtrasoRepository;
	
	public void aplicaRegraAtraso(ContaPagar contaPagar) {
		long diasAtrasado = ChronoUnit.DAYS.between(contaPagar.getDataVencimento(), contaPagar.getDataPagamento());
		if(diasAtrasado > 0) {
			List<RegraAtraso> regras = this.getRegraAtrasoOrderByDiasAtrasoAsc();		
			if(regras != null && !regras.isEmpty()) {
				RegraAtraso regra = regras.stream().filter(r -> r.getDiasAtraso() >= diasAtrasado).findFirst().orElse(regras.get(0));		
				populaContaComAtraso(contaPagar, diasAtrasado, regra);			
			} else {
				populaContaSemAtraso(contaPagar);
			}
		} else {
			populaContaSemAtraso(contaPagar);
		}		
	}

	private void populaContaComAtraso(ContaPagar contaPagar, long diasAtrasado, RegraAtraso regra) {
		BigDecimal valorJuros = this.calculaJurosDia(contaPagar.getValorOriginal(), regra.getPorcentagemJurosDia(), diasAtrasado);
		contaPagar.setValorJuros(valorJuros);
		contaPagar.setPorcentagemJurosDia(regra.getPorcentagemJurosDia());
		
		BigDecimal valorMulta = this.calculaMulta(contaPagar.getValorOriginal(), regra.getPorcentagemMulta());
		contaPagar.setValorMulta(valorMulta);
		contaPagar.setPorcentagemMulta(regra.getPorcentagemMulta());
		
		BigDecimal valorCorrigido = this.calculaValorCorrigido(contaPagar);					
		contaPagar.setValorCorrigido(valorCorrigido);
		contaPagar.setQuantidadeDiasAtraso(diasAtrasado);
	}

	private void populaContaSemAtraso(ContaPagar contaPagar) {
		contaPagar.setValorJuros(BigDecimal.ZERO);
		contaPagar.setPorcentagemJurosDia(BigDecimal.ZERO);
		contaPagar.setValorMulta(BigDecimal.ZERO);
		contaPagar.setPorcentagemMulta(BigDecimal.ZERO);
		contaPagar.setQuantidadeDiasAtraso(0L);
		contaPagar.setValorCorrigido(contaPagar.getValorOriginal());
	}
	
	public BigDecimal calculaValorCorrigido(ContaPagar contaPagar) {
		if(contaPagar != null) {
			if(contaPagar.getValorOriginal() == null) {
				contaPagar.setValorOriginal(BigDecimal.ZERO);
			}
			
			if(contaPagar.getValorMulta() == null) {
				contaPagar.setValorMulta(BigDecimal.ZERO);
			}
			
			if(contaPagar.getValorJuros() == null) {
				contaPagar.setValorJuros(BigDecimal.ZERO);
			}
			
			return contaPagar.getValorOriginal().add(contaPagar.getValorMulta()).add(contaPagar.getValorJuros()).setScale(2, RoundingMode.HALF_EVEN);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	public BigDecimal calculaValorCorrigido(BigDecimal valorOriginal, RegraAtraso regra, long diasAtrasado) {
		return this.calculaValorCorrigido(valorOriginal, regra.getPorcentagemMulta(), regra.getPorcentagemJurosDia(), diasAtrasado);
	}
	
	public BigDecimal calculaValorCorrigido(BigDecimal valorOriginal, BigDecimal porcentagemMulta, BigDecimal porcentagemJurosDia, long diasAtrasado) {
		BigDecimal valorMulta = this.calculaMulta(valorOriginal, porcentagemMulta);
		BigDecimal valorJurosDia = this.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);	
		return valorOriginal.add(valorMulta).add(valorJurosDia).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public BigDecimal calculaMulta(BigDecimal valorOriginal, BigDecimal porcentagemMulta) {
		if(valorOriginal != null && porcentagemMulta != null) {
			return valorOriginal.multiply( porcentagemMulta.divide( BigDecimal.valueOf(100) ) ).setScale(2, RoundingMode.HALF_EVEN);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	public BigDecimal calculaJurosDia(BigDecimal valorOriginal, BigDecimal porcentagemJurosDia, long diasAtrasado) {
		if(valorOriginal != null && porcentagemJurosDia != null && diasAtrasado >= 0) {
			return valorOriginal.multiply( porcentagemJurosDia.divide( BigDecimal.valueOf(100) ) ).multiply( BigDecimal.valueOf(diasAtrasado) ).setScale(2, RoundingMode.HALF_EVEN);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	@Cacheable("regraAtrasoAll")
	public List<RegraAtraso> getRegraAtrasoOrderByDiasAtrasoAsc() {
		return regraAtrasoRepository.findByOrderByDiasAtrasoAsc( );
	}
	
	@CacheEvict(value = "regraAtrasoAll", allEntries = true)
	public void evictCacheRegraAtraso() { 
		// Limpa o cache
	}

}
