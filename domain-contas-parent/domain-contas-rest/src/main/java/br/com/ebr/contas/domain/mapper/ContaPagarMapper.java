package br.com.ebr.contas.domain.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebr.contas.domain.dto.ContaPagarGet;
import br.com.ebr.contas.domain.dto.ContaPagarPost;
import br.com.ebr.contas.domain.dto.ContaPagarPut;
import br.com.ebr.contas.domain.entity.ContaPagar;

@Component
public class ContaPagarMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ContaPagar toEntity(ContaPagarPost contaPagar) {
		return modelMapper.map(contaPagar, ContaPagar.class);
	}

	public ContaPagarPost toModelPost(ContaPagar contaPagarInserida) {
		return modelMapper.map(contaPagarInserida, ContaPagarPost.class);
	}

	public ContaPagarPut toModelPut(ContaPagar contaPagarAtualizada) {
		return modelMapper.map(contaPagarAtualizada, ContaPagarPut.class);
	}

	public List<ContaPagarGet> toCollectionModelGet(List<ContaPagar> contas) {
		if(contas != null) {
			return contas.stream().map(conta -> modelMapper.map(conta, ContaPagarGet.class)).collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}
	
}
