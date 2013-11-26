package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.AreaAtuacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ronaldo
 * @since 26/09/2013
 */
@Repository
public interface AreaAtuacaoRepository extends JPABaseRepository<String, AreaAtuacao> {

    /**
     * Esse metodo esta personalizando a query usando a anotacao @Query
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.at-query
     * @param query
     * @param page
     * @return
     */
    @Query ("SELECT o FROM AreaAtuacao o WHERE o.nome LIKE '%' || :query || '%' ")
    Page<AreaAtuacao> findByNomeLike(String query, Pageable page);
}
