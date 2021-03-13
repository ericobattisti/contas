package br.com.ebr.contas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ebr.contas.domain.entity.RegraAtraso;

@Repository
@Transactional
public interface RegraAtrasoRepository extends JpaRepository<RegraAtraso, Integer> {
	
	List<RegraAtraso> findByOrderByDiasAtrasoAsc();

}
