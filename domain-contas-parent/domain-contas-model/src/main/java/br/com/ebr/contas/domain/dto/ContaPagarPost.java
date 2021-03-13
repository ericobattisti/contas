package br.com.ebr.contas.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;

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

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
