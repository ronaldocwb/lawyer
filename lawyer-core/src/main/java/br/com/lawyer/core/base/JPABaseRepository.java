package br.com.lawyer.core.base;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @param <ID>
 * @param <T>
 * @author Deividi Cavarzan
 */
@SuppressWarnings ("unchecked")
@NoRepositoryBean
public abstract class JPABaseRepository<ID extends Serializable, T extends IUID<ID>> extends SimpleJpaRepository<T, ID> implements Serializable, IJPABaseRepository<ID, T> {

    private static final long serialVersionUID = -7951474930842466983L;

    /**
     * EntityManager - gerencia a entidade
     */
    private final EntityManager manager;

    /**
     * Classe da entidade que ser� manipulada
     */
    private final Class<T> entityClass;

    /**
     * Construtor b�sico para classe de acesso a dados (DAO)
     */
    @SuppressWarnings ("unchecked")
    public JPABaseRepository (Class<T> entityClass, EntityManager em) {
        super(entityClass, em);
        this.entityClass = entityClass;
        this.manager = em;
    }

    public EntityManager getEntityManager () {
        return this.manager;
    }


}