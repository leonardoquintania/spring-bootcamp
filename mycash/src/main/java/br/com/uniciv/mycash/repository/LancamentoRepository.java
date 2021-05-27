package br.com.uniciv.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniciv.mycash.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

}
