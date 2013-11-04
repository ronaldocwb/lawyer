package br.com.lawyer.core.base;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<ID extends Serializable, T extends IUID<ID>, D extends IJPABaseRepository<ID, T>> {

    public List<T> findAll ();

    public List<T> findAll (Sort sort);

    public Page<T> findAll (Pageable pageable);

    public void flush ();

    public T saveAndFlush (T entity);

    public void deleteInBatch (Iterable<T> entities);

    public void deleteAllInBatch ();

    public <S extends T> List<S> save (Iterable<S> entities);

    public <S extends T> S save (S entity);

    public T findOne (ID id);

    public boolean exists (ID id);

    public Iterable<T> findAll (Iterable<ID> ids);

    public void delete (ID id);

    public void delete (T entity);

    public void delete (Iterable<? extends T> entities);

    public void deleteAll ();

    public T findOne (Predicate spec);

    public List<T> findAll (Predicate spec);

    public Page<T> findAll (Predicate spec, Pageable pageable);

    public List<T> findAll (Predicate spec, OrderSpecifier sort);

    public long count (Predicate spec);

    public long count ();

    public T findOne (Specification<T> spec);

    public List<T> findAll (Specification<T> spec);

    public Page<T> findAll (Specification<T> spec, Pageable pageable);

    public List<T> findAll (Specification<T> spec, Sort sort);

    public long count (Specification<T> spec);

    public Usuario getUsuarioLogado() throws BusinessException;

    public LawyerAuthenticationToken getCredenciais() throws BusinessException;

}