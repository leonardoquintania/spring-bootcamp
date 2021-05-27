package br.com.uniciv.mycash.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.uniciv.mycash.domain.Lancamento;
import br.com.uniciv.mycash.repository.LancamentoRepository;

@Service
public class LancamentoService {

	// -- Injeção de dependencia
	@Autowired
	private LancamentoRepository repo;

	public Page<Lancamento> todos(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Lancamento apenasUm(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Lancamento criar(Lancamento lancamento) {
		return repo.save(lancamento);
	}

	public Lancamento atualizar(Integer id, Lancamento novoLancamento) {

		return repo.findById(id).map(lancamento -> {
			lancamento.setDescricao(novoLancamento.getDescricao());
			lancamento.setValor(novoLancamento.getValor());
			lancamento.setTipo(novoLancamento.getTipo());
			lancamento.setData(novoLancamento.getData());

			return repo.save(lancamento);
		}).orElseThrow(() -> new EntityNotFoundException());

	}

	public void excluir(Integer id) {
		repo.deleteById(id);
	}

}
