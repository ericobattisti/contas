CREATE TABLE financeiro.conta_pagar (
	id int8 NOT NULL,
	nome varchar NOT NULL,
	data_vencimento date NOT NULL,
	valor_original numeric NOT NULL,
	data_pagamento date NOT NULL,
	valor_corrigido numeric NOT NULL,
	quantidade_dias_atraso int8 NOT NULL DEFAULT 0,		
	valor_juros numeric NOT NULL,
	porcentagem_juros_dia numeric NOT NULL,
	valor_multa numeric NOT NULL,
	porcentagem_multa numeric NOT NULL,
	CONSTRAINT contas_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN financeiro.conta_pagar.nome IS 'Nome da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.data_vencimento IS 'Data do Vencimento da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.valor_original IS 'Valor Original da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.data_pagamento IS 'Data do Pagamento da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.valor_corrigido IS 'Valor Corrigido da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.quantidade_dias_atraso IS 'Quantidade de dias em atraso no momento do cadastro';
COMMENT ON COLUMN financeiro.conta_pagar.id IS 'Identificador da Conta';
COMMENT ON COLUMN financeiro.conta_pagar.valor_juros IS 'valor do juros';
COMMENT ON COLUMN financeiro.conta_pagar.porcentagem_juros_dia IS 'porcentagem de juros/dia aplicada na conta';
COMMENT ON COLUMN financeiro.conta_pagar.valor_multa IS 'valor da multa';
COMMENT ON COLUMN financeiro.conta_pagar.porcentagem_multa IS 'porcentagem de multa aplicada na conta';


CREATE SEQUENCE financeiro.seq_conta_pagar
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO cycle;
	

CREATE TABLE financeiro.regra_atraso (
	dias_atraso int4 NULL,
	porcentagem_multa numeric NOT NULL,
	porcentagem_juros_dia numeric NOT NULL
);
COMMENT ON COLUMN financeiro.regra_atraso.dias_atraso IS 'Até Dias em Atraso';
COMMENT ON COLUMN financeiro.regra_atraso.porcentagem_multa IS 'Porcentagem da Multa';
COMMENT ON COLUMN financeiro.regra_atraso.porcentagem_juros_dia IS 'Porcentagem do Juros/Dia';
