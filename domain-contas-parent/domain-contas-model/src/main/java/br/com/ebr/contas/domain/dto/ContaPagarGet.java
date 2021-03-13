package br.com.ebr.contas.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(Double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public Integer getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(Integer quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
