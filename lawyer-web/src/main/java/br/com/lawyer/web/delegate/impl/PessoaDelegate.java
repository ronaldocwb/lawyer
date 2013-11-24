package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.service.IPessoaService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IPessoaDelegate;
import br.com.lawyer.web.vo.PessoaVO;
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
public class PessoaDelegate extends BaseDelegate<Pessoa, PessoaVO> implements IPessoaDelegate {

    @Autowired
    private IPessoaService pessoaService;

    @Transactional
    @Override
    public Page<PessoaVO> findPessoaPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Pessoa> pessoas = pessoaService.buscarPorNomeLike(query, pageRequest);
        return getVO(pessoas, pageRequest);
    }

    @Override
    @Transactional
    public PessoaVO salvar (PessoaVO pessoaVO) {
        Pessoa pessoa = pessoaService.salvar(pessoaVO.parse());
        return getVO(pessoa);
    }

    @Override
    public void deletar (String uid) {
        pessoaService.delete(uid);
    }

    @Override
    @Transactional
    public PessoaVO atualizar (PessoaVO pessoaVO, String uid) {
        Pessoa pessoa = pessoaService.atualizar(pessoaVO.parse());
        return getVO(pessoa);
    }

    @Override
    public PessoaVO buscarPorUid (String uid) {
        Pessoa pessoa = pessoaService.findOne(uid);
        return getVO(pessoa);
    }
}
