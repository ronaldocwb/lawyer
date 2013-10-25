package br.com.lawyer.core.base;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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
    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;

    /**
     * Construtor b�sico para classe de acesso a dados (DAO)
     */
    @SuppressWarnings ("unchecked")
    public JPABaseRepository (Class entityClass, EntityManager em) {
        super(entityClass, em);
        this.manager = em;
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityClass);
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(em, builder);
    }

    public EntityManager getEntityManager () {
        return this.manager;
    }

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;


    /*
     * (non-Javadoc)
     * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findOne(com.mysema.query.types.Predicate)
     */
    public T findOne(Predicate predicate) {
        return createQuery(predicate).uniqueResult(path);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
     */
    public List<T> findAll(Predicate predicate) {
        return createQuery(predicate).list(path);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate, com.mysema.query.types.OrderSpecifier<?>[])
     */
    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return createQuery(predicate).orderBy(orders).list(path);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate, org.springframework.data.domain.Pageable)
     */
    public Page<T> findAll(Predicate predicate, Pageable pageable) {

        JPQLQuery countQuery = createQuery(predicate);
        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));

        Long total = countQuery.count();
        List<T> content = total > pageable.getOffset() ? query.list(path) : Collections.<T> emptyList();

        return new PageImpl<T>(content, pageable, total);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#count(com.mysema.query.types.Predicate)
     */
    public long count(Predicate predicate) {
        return createQuery(predicate).count();
    }

    /**
     * Creates a new {@link JPQLQuery} for the given {@link Predicate}.
     *
     * @param predicate
     * @return the Querydsl {@link JPQLQuery}.
     */
    protected JPQLQuery createQuery(Predicate... predicate) {
        return querydsl.createQuery(path).where(predicate);
    }


}