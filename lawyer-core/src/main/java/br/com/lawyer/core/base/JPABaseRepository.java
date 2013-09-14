package br.com.lawyer.core.base;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author Deividi Cavarzan
 * @param <ID>
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class JPABaseRepository<ID extends Serializable, T extends IUID<ID>> extends SimpleJpaRepository<T, ID> implements Serializable, IJPABaseRepository<ID, T> {

    private static final long serialVersionUID = -7951474930842466983L;

    /**
     * EntityManager - gerencia a entidade
     */
    private EntityManager manager;

    /**
     * Classe da entidade que será manipulada
     */
    private Class<T> entityClass;

    /**
     * Construtor básico para classe de acesso a dados (DAO)
     *
     */
    @SuppressWarnings("unchecked")
    public JPABaseRepository (Class<T> entityClass, EntityManager em) {
        // joga para o super que é a classe coringa do spring
        super(entityClass, em);
        this.entityClass = entityClass;
        this.manager = em;
    }

    public EntityManager getEntityManager() {
        return this.manager;
    }

    public void remove(T t) {
        if (t == null) {
            throw new IllegalArgumentException("A entidade a ser excluída deve ser informada.");
        }

        if (t.getUid() == null) {
            throw new IllegalArgumentException("O ID da entidade a ser excluída deve ser informada.");
        }

        t = this.getEntityManager().find(this.entityClass, t.getUid());
        this.getEntityManager().remove(t);
    }

    public T findByPrimaryKey(ID id) {
        return this.getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> find() {
        String listQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e ORDER BY e.id ASC";
        Query query = this.getEntityManager().createQuery(listQuery);

        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<T> find(int offset, int limit) {
        String listQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e ORDER BY e.id ASC";

        Query query = this.getEntityManager().createQuery(listQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    public List<T> findAllByProperty(String property, String value) {

        if (property == null || property.equals("")) {
            throw new IllegalArgumentException("A propriedade deve ser informada.");
        }

        if (value == null || value .equals("")) {
            throw new IllegalArgumentException("Ovalor da propriedade deve ser informado.");
        }

        String listQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e."+ property +" = :values ORDER BY e.id ASC";
        Query query = this.getEntityManager().createQuery(listQuery);
        query.setParameter("values", value);

        return query.getResultList();
    }

    public List<T> findAllByPropertyLike(String property, String value) {
        if (property == null || property.equals("")) {
            throw new IllegalArgumentException("A propriedade deve ser informada.");
        }

        if (value == null || value .equals("")) {
            throw new IllegalArgumentException("Ovalor da propriedade deve ser informado.");
        }

        String listQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e."+ property +" like :values ORDER BY e.id ASC";
        Query query = this.getEntityManager().createQuery(listQuery);
        query.setParameter("values", "%"+value+"%");

        return query.getResultList();
    }

    public T findByProperty(String property, String value) {

        if (property == null || property.equals("")) {
            throw new IllegalArgumentException("A propriedade deve ser informada.");
        }

        if (value == null || value .equals("")) {
            throw new IllegalArgumentException("Ovalor da propriedade deve ser informado.");
        }

        String sqlQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e."+ property +" = :values ORDER BY e.id ASC";
        Query query = this.getEntityManager().createQuery(sqlQuery);
        query.setParameter("values", value);
        return (T) query.getSingleResult();
    }

    public T findByInnerEntityProperty(String entity, String property, String value) {
        if (property == null || property.equals("")) {
            throw new IllegalArgumentException("A propriedade deve ser informada.");
        }

        if (value == null || value.equals("")) {
            throw new IllegalArgumentException("Ovalor da propriedade deve ser informado.");
        }

        if (entity == null || entity.equals("")) {
            throw new IllegalArgumentException("A propriedade deve ser informada.");
        }

        String sqlQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e."+ entity +"."+property+" = :values ORDER BY e.id ASC";
        Query query = this.getEntityManager().createQuery(sqlQuery );
        query.setParameter("values", value);

        return (T) query.getSingleResult();
    }

}