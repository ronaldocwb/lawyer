package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.PessoaService;
import br.com.lawyer.web.delegate.PessoaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 10/10/2013
 */
@Service
public class PessoaDelegateImpl implements PessoaDelegate {

    @Autowired
    private PessoaService pessoaService;

    @Transactional
    @Override
    public Page<Pessoa> findPessoaPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Pessoa> pessoas = pessoaService.buscarPorNomeLike(query, pageRequest);
        return pessoas;
    }

    @Override
    @Transactional
    public Pessoa salvar (Pessoa pessoaVO) throws BusinessException {
        Pessoa pessoa = pessoaService.salvar(pessoaVO);
        return pessoa;
    }

    @Override
    @Transactional
    public void deletar (String uid) throws BusinessException {
        pessoaService.deletar(uid);
    }

    @Override
    @Transactional
    public Pessoa atualizar (Pessoa pessoaVO, String uid) throws BusinessException {
        Pessoa pessoa = pessoaService.atualizar(pessoaVO);
        return pessoa;
    }

    @Override
    public Pessoa buscarPorUid (String uid) {
        Pessoa pessoa = pessoaService.findOne(uid);
        return pessoa;
    }
}
