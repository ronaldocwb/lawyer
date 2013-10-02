package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Repository
public interface IEmpresaRepository extends IJPABaseRepository<String, Empresa> {

    /**
     * Esse metodo está personalizando a query usando a anotação @Query
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.at-query
     * @param query
     * @param page
     * @return
     */
    @Query ("SELECT o FROM Empresa o WHERE o.nomeFantasia LIKE '%' || :query || '%' OR o.razaoSocial LIKE '%' || :query || '%' ")
    Page<Empresa> findByRazaoSocialOrNomeFantasiaLike(String query, Pageable page);

    /**
     * Esse metodo equivale ao metodo acime: findByRazaoSocialOrNomeFantasiaLike
     * Mas aqui o Spring gera a query automaticamente.
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
     * @param queryRazao
     * @param queryNome
     * @param page
     * @return
     */
    Page<Empresa> findByRazaoSocialContainingOrNomeFantasiaContaining (String queryRazao, String queryNome, Pageable page);
}
