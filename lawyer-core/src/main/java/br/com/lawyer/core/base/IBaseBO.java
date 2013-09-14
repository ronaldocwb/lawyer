package br.com.lawyer.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

public interface IBaseBO<ID extends Serializable, T extends IUID<ID>, D extends IJPABaseRepository<ID, T>> {

    public T findByPrimaryKey(ID id);

    public List<T> find(int offset, int limit);

    public List<T> findAll();

    public List<T> find();

    public List<T> findAll(Sort sort);
    
    public Page<T> findAll(Pageable pageable);
    
    public void flush ();
    
    public T saveAndFlush (T entity);
    
    public void deleteInBatch (Iterable<T> entities);
    
    public void deleteAllInBatch () ;
    
    public <S extends T> List<S> save (Iterable<S> entities);
    
    public <S extends T> S save (S entity);
    
    public T findOne (ID id);

    public boolean exists (ID id);

    public Iterable<T> findAll (Iterable<ID> ids);

    public void delete (ID id);
    
    public void delete (T entity);
    
    public void delete (Iterable<? extends T> entities);

    public void deleteAll ();
    
    public T findOne (Specification<T> spec);
    
    public List<T> findAll (Specification<T> spec);
    
    public Page<T> findAll (Specification<T> spec, Pageable pageable);
    
    public List<T> findAll (Specification<T> spec, Sort sort);
    
    public long count (Specification<T> spec);

    public long count();

    public List<T> findAllByProperty(String property, String value);

    public List<T> findAllByPropertyLike(String property, String value);

    public T findByProperty(String property, String value);

    public T findByInnerEntityProperty(String entity, String property, String value);

}