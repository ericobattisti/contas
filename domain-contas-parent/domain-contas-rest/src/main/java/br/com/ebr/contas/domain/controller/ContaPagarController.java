package br.com.ebr.contas.domain.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebr.contas.domain.dto.ContaPagarGet;
import br.com.ebr.contas.domain.dto.ContaPagarPost;
import br.com.ebr.contas.domain.dto.ContaPagarPut;
import br.com.ebr.contas.domain.dto.RegraAtrasoGet;
import br.com.ebr.contas.domain.entity.ContaPagar;
import br.com.ebr.contas.domain.entity.RegraAtraso;
import br.com.ebr.contas.domain.mapper.ContaPagarMapper;
import br.com.ebr.contas.domain.mapper.RegraAtrasoMapper;
import br.com.ebr.contas.domain.service.ContaPagarService;
import br.com.ebr.contas.domain.service.RegraAtrasoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "contas-pagar")
@ApiModel(description = "Gerenciamento das contas.")
public class ContaPagarController {
	
	@Autowired
	private ContaPagarService contaService;
	
	@Autowired
	private RegraAtrasoService regraAtrasoService;
	
	@Autowired
    private ContaPagarMapper contaPagarMapper;
	
	@Autowired
    private RegraAtrasoMapper regraAtrasoMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE,value="Insere contas a pagar",code=201)
    public ResponseEntity<ContaPagarPost> inserir(@RequestBody(required = true) ContaPagarPost contaPagar) {
		ContaPagar contaPagarInserida = contaService.salvar( contaPagarMapper.toEntity(contaPagar) );	
		
		return ResponseEntity.created( URI.create("/contas-pagar/" + contaPagarInserida.getId()) ).body( contaPagarMapper.toModelPost(contaPagarInserida) );
    }
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE,value="Atualiza contas a pagar",code=201)
    public ResponseEntity<ContaPagarPut> atualizar(@PathVariable(value = "id") Long idConta, @RequestBody(required = true) ContaPagarPut contaPagar) {
		if(idConta.equals(contaPagar.getId())) {		
			ContaPagar contaPagarAtualizada = contaService.salvar( contaPagarMapper.toEntity(contaPagar) );
			
			return ResponseEntity.ok( contaPagarMapper.toModelPut(contaPagarAtualizada) );
		} else {
			return ResponseEntity.badRequest().build();
		}
    }
	
	@DeleteMapping(path = "{id}")
	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE, value="Deleta contas a pagar",code=201)
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long idConta) {
		contaService.deletar(idConta);
		
		return ResponseEntity.ok( ).build();
    }
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE,value="Obtém as contas a pagar",code=200)
    public ResponseEntity<List<ContaPagarGet>> buscaTodos() {
		List<ContaPagar> contas = contaService.buscaTodos();
		
		return ResponseEntity.ok( contaPagarMapper.toCollectionModelGet(contas) );
    }
	
	@GetMapping(path = "regras-atraso", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE,value="Obtem as regras de atrasos das contas a pagar",code=200)
    public ResponseEntity<List<RegraAtrasoGet>> buscaRegraAtraso() {
		List<RegraAtraso> regras = regraAtrasoService.getRegraAtrasoOrderByDiasAtrasoAsc();
		
		return ResponseEntity.ok( regraAtrasoMapper.toCollectionModelGet(regras) );
    }

}
