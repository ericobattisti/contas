package br.com.ebr.contas.domain.controller.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Classe que representa a inclusão da conta a pagar.")
public class ContaPagarPost implements Serializable {
	private static final long serialVersionUID = 804013592740465381L;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Double valorOriginal;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimento;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataPagamento;

}
