package br.com.ebr.contas.domain.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Classe que representa a regra de atraso da conta a pagar.")
public class RegraAtrasoGet implements Serializable {

	private static final long serialVersionUID = -7407410407346980231L;

	@NotNull
	private Integer diasAtraso;
	
	@NotNull
	private Double multa;
	
	@NotNull
	private Double jurosDia;

}
