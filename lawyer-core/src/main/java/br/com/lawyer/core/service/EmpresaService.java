package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.repository.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface EmpresaService extends BaseService<String, Empresa, EmpresaRepository> {

    Page<Empresa> buscarPorRazaoSocialOuNomeFantasiaLike (String query, PageRequest pageRequest);

    void deletarEmpresa (String uid);
}
