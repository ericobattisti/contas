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

import lombok.Data;

@Data
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

}
