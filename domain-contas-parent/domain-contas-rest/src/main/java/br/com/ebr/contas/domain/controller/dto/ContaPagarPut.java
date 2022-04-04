package br.com.ebr.contas.domain.controller.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Classe que representa a alteração da conta a pagar.")
public class ContaPagarPut extends ContaPagarPost {
	
	private static final long serialVersionUID = -7360365907469731791L;
	
	@NotNull
	private Long id;

}
