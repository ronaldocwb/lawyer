package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@NoRepositoryBean
public interface ContatoRepository extends JpaRepository<Contato, String>,JPABaseRepository<String,Contato> {

    Page<Contato> findClientes (String query, String tipo, Pageable pageRequest);

    void remover (String uid, Class<?> klazz);
}
