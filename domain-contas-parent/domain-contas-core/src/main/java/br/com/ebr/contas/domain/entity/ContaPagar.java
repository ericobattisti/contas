package br.com.ebr.contas.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conta_pagar")
public class ContaPagar implements Serializable {

	private static final long serialVersionUID = 2381279837893181662L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contas_seq_gen")
	@SequenceGenerator(name = "contas_seq_gen", sequenceName = "seq_conta_pagar", allocationSize = 1)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	@Column(name = "data_vencimento", columnDefinition = "DATE")
	private LocalDate dataVencimento;

	@NotNull
	@Column(name = "valor_original")
	private BigDecimal valorOriginal;

	@NotNull
	@Column(name = "data_pagamento", columnDefinition = "DATE")
	private LocalDate dataPagamento;

	@NotNull
	@Column(name = "valor_corrigido")
	private BigDecimal valorCorrigido;

	@NotNull
	@Column(name = "quantidade_dias_atraso")
	private Long quantidadeDiasAtraso;

	@Column(name = "valor_juros")
	private BigDecimal valorJuros;

	@Column(name = "porcentagem_juros_dia")
	private BigDecimal porcentagemJurosDia;

	@Column(name = "valor_multa")
	private BigDecimal valorMulta;

	@Column(name = "porcentagem_multa")
	private BigDecimal porcentagemMulta;

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

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(BigDecimal valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public Long getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(Long quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}

	public BigDecimal getPorcentagemJurosDia() {
		return porcentagemJurosDia;
	}

	public void setPorcentagemJurosDia(BigDecimal porcentagemJurosDia) {
		this.porcentagemJurosDia = porcentagemJurosDia;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getPorcentagemMulta() {
		return porcentagemMulta;
	}

	public void setPorcentagemMulta(BigDecimal porcentagemMulta) {
		this.porcentagemMulta = porcentagemMulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagar other = (ContaPagar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
