package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface IPessoaRepository extends IJPABaseRepository<String, Pessoa> {

    /**
     * Esse metodo está personalizando a query usando a anotação @Query
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.at-query
     * @param query
     * @param page
     * @return
     */
    @Query ("SELECT o FROM Pessoa o WHERE o.nome LIKE '%' || :query || '%' ")
    Page<Pessoa> findByRazaoSocialOrNomeFantasiaLike(String query, Pageable page);

    /**
     * Esse metodo equivale ao metodo acime: findByRazaoSocialOrNomeFantasiaLike
     * Mas aqui o Spring gera a query automaticamente.
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
     * @param query
     * @param page
     * @return
     */
    Page<Pessoa> findByNomeContaining(String query, Pageable page);

    List<Pessoa> findByEmpresaUid (String uid);

}