package br.com.ebr.contas.domain;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ebr.contas.domain.entity.ContaPagar;
import br.com.ebr.contas.domain.entity.RegraAtraso;
import br.com.ebr.contas.domain.repository.RegraAtrasoRepository;
import br.com.ebr.contas.domain.service.RegraAtrasoService;

class RegraAtrasoServiceTest {
	
	private AutoCloseable closeable;
	
	@Mock
	private RegraAtrasoRepository regraAtrasoRepository;

	@InjectMocks
	private RegraAtrasoService service;
	
	@BeforeEach
	void setUp() throws IOException {
		closeable = MockitoAnnotations.openMocks(this);
		
		List<RegraAtraso> regraAtrasos = List.of(
			RegraAtraso.builder().diasAtraso(-1).porcentagemMulta(BigDecimal.valueOf(5)).porcentagemJurosDia(BigDecimal.valueOf(0.3)).build(),
			RegraAtraso.builder().diasAtraso(3).porcentagemMulta(BigDecimal.valueOf(2)).porcentagemJurosDia(BigDecimal.valueOf(0.1)).build(),
			RegraAtraso.builder().diasAtraso(5).porcentagemMulta(BigDecimal.valueOf(3)).porcentagemJurosDia(BigDecimal.valueOf(0.2)).build()
		);
		
		when(regraAtrasoRepository.findByOrderByDiasAtrasoAsc()).thenReturn(regraAtrasos);
	}
	
	@AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

	@Test
	void aplicaRegraAtrasoContaEmDia() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 11));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10500.2, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(0, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(0, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoNoVencimento() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 12));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10500.2, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(0, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(0, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado1() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 13));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10720.70, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(10.50, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(210.00, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado2() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 14));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10731.20, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(21.00, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(210.00, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado3() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 15));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10741.70, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(31.50, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(210.00, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado4() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 16));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10899.21, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(84.00, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(315.01, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado5() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 17));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(10920.21, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(105.00, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(315.01, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado6() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 18));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(11214.21, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(189.00, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(525.01, contaPagar.getValorMulta().doubleValue());
	}
	
	@Test
	void aplicaRegraAtrasoDiasAtrasado7() {
		// given
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setNome("Contas a Pagar 1");
		contaPagar.setValorOriginal(BigDecimal.valueOf(10500.2));
		contaPagar.setDataVencimento(LocalDate.of(2021, Month.MARCH, 12));
		contaPagar.setDataPagamento(LocalDate.of(2021, Month.MARCH, 19));
		
		// when
		service.aplicaRegraAtraso(contaPagar);

		// then
		Assertions.assertEquals(11245.71, contaPagar.getValorCorrigido().doubleValue());
		Assertions.assertEquals(220.50, contaPagar.getValorJuros().doubleValue());
		Assertions.assertEquals(525.01, contaPagar.getValorMulta().doubleValue());
	}

	@Test
	void calculaJurosDiaComValorOriginalNull() {
		// given
		BigDecimal valorOriginal = null;
		BigDecimal porcentagemJurosDia = BigDecimal.valueOf(0.1);
		long diasAtrasado = 2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComValorOriginalZero() {
		// given
		BigDecimal valorOriginal = BigDecimal.ZERO;
		BigDecimal porcentagemJurosDia = BigDecimal.valueOf(0.1);
		long diasAtrasado = 2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComPorcentagemJurosDiaZero() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemJurosDia = BigDecimal.ZERO;
		long diasAtrasado = 2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComPorcentagemJurosDiaNull() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemJurosDia = null;
		long diasAtrasado = 2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComDiasAtrasadoNegativo() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemJurosDia = BigDecimal.valueOf(0.1);
		long diasAtrasado = -2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComDiasAtrasadoZero() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemJurosDia = BigDecimal.valueOf(0.1);
		long diasAtrasado = 0;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaJurosDiaComDiasAtrasadoPositivo() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemJurosDia = BigDecimal.valueOf(0.1);
		long diasAtrasado = 2;

		// when
		BigDecimal result = service.calculaJurosDia(valorOriginal, porcentagemJurosDia, diasAtrasado);

		// then
		Assertions.assertEquals(21, result.doubleValue());
	}

	@Test
	void calculaMultaComValorOriginalNull() {
		// given
		BigDecimal valorOriginal = null;
		BigDecimal porcentagemMulta = BigDecimal.valueOf(2);

		// when
		BigDecimal result = service.calculaMulta(valorOriginal, porcentagemMulta);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaMultaComValorOriginalZero() {
		// given
		BigDecimal valorOriginal = BigDecimal.ZERO;
		BigDecimal porcentagemMulta = BigDecimal.valueOf(2);

		// when
		BigDecimal result = service.calculaMulta(valorOriginal, porcentagemMulta);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaMultaComPorcentagemMultaZero() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemMulta = BigDecimal.ZERO;

		// when
		BigDecimal result = service.calculaMulta(valorOriginal, porcentagemMulta);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaMultaComPorcentagemMultaNull() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemMulta = null;

		// when
		BigDecimal result = service.calculaMulta(valorOriginal, porcentagemMulta);

		// then
		Assertions.assertEquals(0, result.doubleValue());
	}

	@Test
	void calculaMultaComValorOriginal() {
		// given
		BigDecimal valorOriginal = BigDecimal.valueOf(10500.20);
		BigDecimal porcentagemMulta = BigDecimal.valueOf(2);

		// when
		BigDecimal result = service.calculaMulta(valorOriginal, porcentagemMulta);

		// then
		Assertions.assertEquals(210, result.doubleValue());
	}

}
