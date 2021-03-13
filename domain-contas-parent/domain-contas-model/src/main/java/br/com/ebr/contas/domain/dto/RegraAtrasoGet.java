package br.com.ebr.contas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe que representa a regra de atraso da conta a pagar.")
public class RegraAtrasoGet implements Serializable {

	private static final long serialVersionUID = -7407410407346980231L;

	@NotNull
	private Integer diasAtraso;
	
	@NotNull
	private Double multa;
	
	@NotNull
	private Double jurosDia;

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getJurosDia() {
		return jurosDia;
	}

	public void setJurosDia(Double jurosDia) {
		this.jurosDia = jurosDia;
	}

}
