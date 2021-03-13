package br.com.ebr.contas.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	public RegraAtraso() {
		
	}	

	public RegraAtraso(Integer diasAtraso, BigDecimal porcentagemMulta, BigDecimal porcentagemJurosDia) {
		this.diasAtraso = diasAtraso;
		this.porcentagemMulta = porcentagemMulta;
		this.porcentagemJurosDia = porcentagemJurosDia;
	}

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public BigDecimal getPorcentagemMulta() {
		return porcentagemMulta;
	}

	public void setPorcentagemMulta(BigDecimal porcentagemMulta) {
		this.porcentagemMulta = porcentagemMulta;
	}

	public BigDecimal getPorcentagemJurosDia() {
		return porcentagemJurosDia;
	}

	public void setPorcentagemJurosDia(BigDecimal porcentagemJurosDia) {
		this.porcentagemJurosDia = porcentagemJurosDia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diasAtraso == null) ? 0 : diasAtraso.hashCode());
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
		RegraAtraso other = (RegraAtraso) obj;
		if (diasAtraso == null) {
			if (other.diasAtraso != null)
				return false;
		} else if (!diasAtraso.equals(other.diasAtraso))
			return false;
		return true;
	}

}
