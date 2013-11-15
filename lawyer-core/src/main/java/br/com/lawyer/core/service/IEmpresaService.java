package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.repository.IEmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface IEmpresaService extends IBaseService<String, Empresa, IEmpresaRepository> {

    Page<Empresa> buscarPorRazaoSocialOuNomeFantasiaLike (String query, PageRequest pageRequest);

    void deletarEmpresa (String uid);
}
