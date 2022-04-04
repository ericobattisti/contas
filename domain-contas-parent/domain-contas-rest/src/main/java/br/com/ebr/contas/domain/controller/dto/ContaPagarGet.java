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
@ApiModel(description = "Classe que representa a conta a pagar.")
public class ContaPagarGet implements Serializable {
	private static final long serialVersionUID = -1595859969804859038L;
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Double valorOriginal;
	
	@NotNull
	private Double valorCorrigido;
	
	@NotNull
	private Integer quantidadeDiasAtraso;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataPagamento;

}
