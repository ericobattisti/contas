package br.com.ebr.contas.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "regra_atraso")
public class RegraAtraso implements Serializable {

	private static final long serialVersionUID = -8912489043031654185L;

	@Id
	@Column(name = "dias_atraso")
	private Integer diasAtraso;

	@Column(name = "porcentagem_multa")
	private BigDecimal porcentagemMulta;

	@Column(name = "porcentagem_juros_dia")
	private BigDecimal porcentagemJurosDia;

}
