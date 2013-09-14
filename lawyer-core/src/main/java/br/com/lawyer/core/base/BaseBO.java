package br.com.lawyer.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Estabelece os comportamentos básicos de negócio de uma entidade.
 *
 * @author Deividi Cavarzan
 *
 * @param <ID> tipo da chave primária da entidade.
 * @param <T> tipo da entidade.
 * @param <D> tipo do DAO.
 */
public abstract class BaseBO<ID extends Serializable, T extends IUID<ID>, D extends IJPABaseRepository<ID, T>> implements Serializable, IBaseBO<ID, T, D> {

    private static final long serialVersionUID = 8080307118544118690L;

    private D dao;

    /**
     * Construtor
     *
     * @param dao - DAO que será utilizado referente a entidade manipulada
     */
    public BaseBO (D dao) {
        this.dao = dao;
    }

    /**
     * Retorna o dao para manipulação da entidade
     *
     * @return
     */
    public D getDAO() {
        return this.dao;
    }

    public T findByPrimaryKey(ID id) {
        return this.getDAO().findByPrimaryKey(id);
    }

    public List<T> find() {
        return this.getDAO().find();
    }

    public List<T> find(int offset, int limit) {
        return this.getDAO().find(offset, limit);
    }

    public long count() {
        return (int) this.getDAO().count();
    }

    public List<T> findAll() {
        return this.getDAO().findAll();
    }

    @Override
    public List<T> findAll (Sort sort) {
        return getDAO().findAll(sort);
    }

    @Override
    public Page<T> findAll (Pageable pageable) {
        return getDAO().findAll(pageable);
    }

    @Override
    public void flush () {
        getDAO().flush();
    }

    @Override
    public T saveAndFlush (T entity) {
        return getDAO().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch (Iterable<T> entities) {
        getDAO().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch () {
        getDAO().deleteAllInBatch();
    }

    @Override
    public <S extends T> List<S> save (Iterable<S> entities) {
        return getDAO().save(entities);
    }

    @Override
    public <S extends T> S save (S entity) {
        return getDAO().save(entity);
    }

    @Override
    public T findOne (ID id) {
        return getDAO().findOne(id);
    }

    @Override
    public boolean exists (ID id) {
        return getDAO().exists(id);
    }

    @Override
    public Iterable<T> findAll (Iterable<ID> ids) {
        return getDAO().findAll(ids);
    }

    @Override
    public void delete (ID id) {
        getDAO().delete(id);
    }

    @Override
    public void delete (T entity) {
        getDAO().delete(entity);
    }

    @Override
    public void delete (Iterable<? extends T> entities) {
        getDAO().delete(entities);
    }

    @Override
    public void deleteAll() {
        getDAO().deleteAll();
    }

    @Override
    public T findOne (Specification<T> spec) {
        return getDAO().findOne(spec);
    }

    @Override
    public List<T> findAll (Specification<T> spec) {
        return getDAO().findAll(spec);
    }

    @Override
    public Page<T> findAll (Specification<T> spec, Pageable pageable) {
        return getDAO().findAll(spec, pageable);
    }

    @Override
    public List<T> findAll (Specification<T> spec, Sort sort) {
        return getDAO().findAll(spec, sort);
    }

    @Override
    public long count (Specification<T> spec) {
        return getDAO().count(spec);
    }

    public List<T> findAllByProperty(String property, String value) {
        return getDAO().findAllByProperty(property,value);
    }

    public List<T> findAllByPropertyLike(String property, String value) {
        return getDAO().findAllByPropertyLike(property,value);
    }

    public T findByProperty(String property, String value) {
        return getDAO().findByProperty(property,value);
    }

    public T findByInnerEntityProperty(String entity, String property, String value) {
        return getDAO().findByInnerEntityProperty(entity, property, value);
    }

}