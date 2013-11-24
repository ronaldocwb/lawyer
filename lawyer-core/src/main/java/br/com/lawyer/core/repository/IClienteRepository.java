package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@NoRepositoryBean
public interface IClienteRepository extends IJPABaseRepository<String, Cliente> {

    Page<Cliente> findClientes (String query, Pageable pageRequest);

    void remover (String uid, Class<?> klazz);

    Cliente salvar (Cliente cliente);
}
