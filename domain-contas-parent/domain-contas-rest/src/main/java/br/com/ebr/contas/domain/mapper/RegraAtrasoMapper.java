package br.com.ebr.contas.domain.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebr.contas.domain.controller.dto.RegraAtrasoGet;
import br.com.ebr.contas.domain.entity.RegraAtraso;

@Component
public class RegraAtrasoMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public List<RegraAtrasoGet> toCollectionModelGet(List<RegraAtraso> regraAtrasos) {
		if(regraAtrasos != null) {
			return regraAtrasos.stream().map(regra -> modelMapper.map(regra, RegraAtrasoGet.class)).collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}
	
}
