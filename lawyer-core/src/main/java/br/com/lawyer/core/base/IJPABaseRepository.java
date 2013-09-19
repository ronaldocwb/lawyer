package br.com.lawyer.core.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Descreve o contrato de uma entidade que possui uma chave (IUID)
 *
 * @author Deividi Cavarzan
 *
 */
@NoRepositoryBean
public interface IJPABaseRepository<B extends Serializable, T extends IUID<B>> extends JpaRepository<T, B>, JpaSpecificationExecutor<T> {

    /**
     * Retorna o entityManager.
     *
     * @return o entityManager.
     */
    EntityManager getEntityManager();

    /**
     * Pesquisa pela entidade por chave primária.
     *
     * @param b a chave primária da entidade a ser pesquisada.
     * @return a entidade pesquisada ou <code>null</code> caso a entidade não exista.
     */
    T findByPrimaryKey(B b);

    /**
     * Lista todas as entidades.
     *
     * @param offset a posição da primeira entidade retornada.
     * @param limit número máximo de entidades retornadas.
     * @return uma lista com todas as entidades.
     */
    List<T> find(int offset, int limit);

    public List<T> findAllByProperty(String property, String value);

    public List<T> findAllByPropertyLike(String property, String value);

    public T findByProperty(String property, String value);

    public T findByInnerEntityProperty(String entity, String property, String value);
}