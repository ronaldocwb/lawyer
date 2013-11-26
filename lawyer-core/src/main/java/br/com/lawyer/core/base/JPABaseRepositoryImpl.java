package br.com.lawyer.core.base;

import br.com.lawyer.core.entity.Usuario;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class JPABaseRepositoryImpl<ID extends Serializable, T extends IUID<ID>> implements Serializable, JPABaseRepository<ID, T> {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        configure(Usuario.class, this.entityManager);
    }

    private static final long serialVersionUID = -7951474930842466983L;

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;
    protected SimpleJpaRepository<T, ID> jpaRepository;
    protected EntityManager manager;
    protected EntityPath<T> path;
    protected PathBuilder<T> builder;
    protected Querydsl querydsl;

    public JPABaseRepositoryImpl() {}

    public JPABaseRepositoryImpl(Class entityClass, EntityManager em) {
        this.manager = em;
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityClass);
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(em, builder);
        this.jpaRepository = new SimpleJpaRepository<T, ID>(entityClass, em);
    }

    protected void configure(Class entityClass, EntityManager em) {
        this.manager = em;
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityClass);
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(em, builder);
        this.jpaRepository = new SimpleJpaRepository<T, ID>(entityClass, em);
    }

    protected EntityManager getEntityManager() {
        return this.manager;
    }

    public T findOne(Predicate predicate) {
        return createQuery(predicate).uniqueResult(path);
    }

    public List<T> findAll(Predicate predicate) {
        return createQuery(predicate).list(path);
    }

    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return createQuery(predicate).orderBy(orders).list(path);
    }

    public Page<T> findAll(Predicate predicate, Pageable pageable) {

        JPQLQuery countQuery = createQuery(predicate);
        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));

        Long total = countQuery.count();
        List<T> content = total > pageable.getOffset() ? query.list(path) : Collections.<T> emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<T> findAll(JPQLQuery query, Pageable pageable) {

        JPQLQuery pagedQuery =  querydsl.applyPagination(pageable, query);
        long total = query.count();

        List<T> content = total > pageable.getOffset() ? pagedQuery.list(path) : Collections.<T> emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    public long count(Predicate predicate) {
        return createQuery(predicate).count();
    }

    protected JPQLQuery createQuery(Predicate... predicate) {
        return querydsl.createQuery(path).where(predicate);
    }

    @Override
    public List<T> findAll () {
        return this.jpaRepository.findAll();
    }

    @Override
    public List<T> findAll (Sort sort) {
        return this.jpaRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll (Pageable pageable) {
        return this.jpaRepository.findAll(pageable);
    }

    @Override
    public List<T> findAll (Iterable<ID> ids) {
        return this.jpaRepository.findAll(ids);
    }

    @Override
    public long count () {
        return this.jpaRepository.count();
    }

    @Override
    public void delete (ID id) {
        this.jpaRepository.delete(id);
    }

    @Override
    public void delete (T entity) {
        this.jpaRepository.delete(entity);
    }

    @Override
    public void delete (Iterable<? extends T> entities) {
        this.jpaRepository.delete(entities);
    }

    @Override
    public void deleteAll () {
        this.jpaRepository.deleteAll();
    }

    @Override
    public <S extends T> S save (S entity) {
        return this.jpaRepository.save(entity);
    }

    @Override
    public <S extends T> List<S> save (Iterable<S> entities) {
        return this.jpaRepository.save(entities);
    }

    @Override
    public T findOne (ID id) {
        return this.jpaRepository.findOne(id);
    }

    @Override
    public boolean exists (ID id) {
        return this.jpaRepository.exists(id);
    }

    @Override
    public void flush () {
        this.jpaRepository.flush();
    }

    @Override
    public T saveAndFlush (T entity) {
        return this.jpaRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch (Iterable<T> entities) {
        this.jpaRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch () {
        this.jpaRepository.deleteAllInBatch();
    }

    @Override
    public T findOne (Specification<T> spec) {
        return this.jpaRepository.findOne(spec);
    }

    @Override
    public List<T> findAll (Specification<T> spec) {
        return this.jpaRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll (Specification<T> spec, Pageable pageable) {
        return this.jpaRepository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll (Specification<T> spec, Sort sort) {
        return this.jpaRepository.findAll(spec, sort);
    }

    @Override
    public long count (Specification<T> spec) {
        return this.jpaRepository.count(spec);
    }
}