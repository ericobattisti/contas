package br.com.ebr.contas.domain.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe que representa a alteração da conta a pagar.")
public class ContaPagarPut extends ContaPagarPost {
	
	private static final long serialVersionUID = -7360365907469731791L;
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
