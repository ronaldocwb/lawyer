package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.repository.PessoaRepository;
import br.com.lawyer.core.repository.predicates.PessoaPredicate;
import br.com.lawyer.core.service.ClienteService;
import br.com.lawyer.core.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class PessoaServiceImpl extends BaseServiceImpl<String, Pessoa, PessoaRepository> implements PessoaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    public PessoaServiceImpl (PessoaRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por pagina��o informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.Pessoa#nome}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<Pessoa>
     */
    @Override
    public Page<Pessoa> buscarPorNomeLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(PessoaPredicate.nomeIsLike(query), pageRequest);
    }

    @Override
    public void removerReferenciaDaEmpresa (String uid) {
        List<Pessoa> pessoas = getRepository().findByEmpresaUid(uid);
        for (Pessoa pessoa : pessoas) {
            pessoa.setEmpresa(null);
        }
    }

    @Override
    public Pessoa salvar (Pessoa pessoa) {
        saveAndFlush(pessoa);
        if (pessoa.getCliente().equals(Boolean.TRUE)) {
            Cliente cliente = new Cliente();
            cliente.setPessoa(pessoa);
            clienteService.save(cliente);
        }
        return pessoa;
    }

    @Override
    public Pessoa atualizar (Pessoa pessoa) {
        Pessoa storedPessoa = findOne(pessoa.getUid());
        if (storedPessoa != null && !storedPessoa.getCliente().equals(pessoa.getCliente())) {
            if (pessoa.getCliente().equals(Boolean.TRUE)) {
                clienteService.save(new Cliente(pessoa));
            } else {
                clienteService.removerPorReferenciaUid(storedPessoa.getUid(), Pessoa.class);
            }
        }
        save(pessoa);
        return pessoa;
    }
}
