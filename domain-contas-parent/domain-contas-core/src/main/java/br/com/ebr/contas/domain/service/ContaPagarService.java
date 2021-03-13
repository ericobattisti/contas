package br.com.ebr.contas.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ebr.contas.domain.entity.ContaPagar;
import br.com.ebr.contas.domain.repository.ContaPagarRepository;

@Service
public class ContaPagarService {

	@Autowired
	private ContaPagarRepository contaPagarRepository;
	
	@Autowired
	private RegraAtrasoService regraAtrasoService;

	public ContaPagar salvar(ContaPagar contaPagar) {		
		regraAtrasoService.aplicaRegraAtraso(contaPagar);
		return contaPagarRepository.save(contaPagar);
	}
	
	public void deletar(Long idConta) {
		contaPagarRepository.deleteById(idConta);
	}

	public List<ContaPagar> buscaTodos() {
		return contaPagarRepository.findByOrderByDataVencimentoDesc();
	}

}
