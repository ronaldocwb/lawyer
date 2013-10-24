package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Advogado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
public interface IAdvogadoRepository  extends IJPABaseRepository<String, Advogado> {

    /**
     * Esse metodo esta personalizando a query usando a anotacaoo @Query
     * @link http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.at-query
     * @param query
     * @param page
     * @return
     */
    @Query ("SELECT o FROM Advogado o WHERE o.pessoa.nome LIKE '%' || :query || '%' ")
    Page<Advogado> findByNameContaining(String query, Pageable page);

}