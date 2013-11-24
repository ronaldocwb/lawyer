package br.com.lawyer.core.base;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.List;

/**
 * Estabelece os comportamentos b�sicos de neg�cio de uma entidade.
 *
 * @param <ID> tipo da chave prim�ria da entidade.
 * @param <T>  tipo da entidade.
 * @param <D>  tipo do DAO.
 * @author Deividi Cavarzan
 */
public abstract class BaseService<ID extends Serializable, T extends IUID<ID>, D extends IJPABaseRepository<ID, T>> implements Serializable, IBaseService<ID, T, D> {

    private static final long serialVersionUID = 8080307118544118690L;

    private final D repository;

    public BaseService (D repository) {
        this.repository = repository;
    }

    /**
     * Retorna o repository para manipula��o da entidade
     *
     * @return
     */
    public D getRepository () {
        return this.repository;
    }

    public long count () {
        return (int) this.getRepository().count();
    }

    public List<T> findAll () {
        return this.getRepository().findAll();
    }

    @Override
    public List<T> findAll (Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public Page<T> findAll (Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public void flush () {
        getRepository().flush();
    }

    @Override
    public T saveAndFlush (T entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch (Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch () {
        getRepository().deleteAllInBatch();
    }

    @Override
    public <S extends T> List<S> save (Iterable<S> entities) {
        return getRepository().save(entities);
    }

    @Override
    public <S extends T> S save (S entity) {
        return getRepository().save(entity);
    }

    @Override
    public T findOne (ID id) {
        return getRepository().findOne(id);
    }

    @Override
    public boolean exists (ID id) {
        return getRepository().exists(id);
    }

    @Override
    public Iterable<T> findAll (Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    @Override
    public void delete (ID id) {
        getRepository().delete(id);
    }

    @Override
    public void delete (T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete (Iterable<? extends T> entities) {
        getRepository().delete(entities);
    }

    @Override
    public void deleteAll () {
        getRepository().deleteAll();
    }

    @Override
    public T findOne (Predicate spec) {
        return getRepository().findOne(spec);
    }

    @Override
    public List<T> findAll (Predicate spec) {
        return (List<T>) getRepository().findAll(spec);
    }

    @Override
    public Page<T> findAll (Predicate spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable);
    }

    @Override
    public List<T> findAll (Predicate spec, OrderSpecifier sort) {
        return (List<T>) getRepository().findAll(spec, sort);
    }

    @Override
    public long count (Predicate spec) {
        return getRepository().count(spec);
    }

    @Override
    public T findOne (Specification<T> spec) {
        return getRepository().findOne(spec);
    }

    @Override
    public List<T> findAll (Specification<T> spec) {
        return getRepository().findAll(spec);
    }

    @Override
    public Page<T> findAll (Specification<T> spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable);
    }

    @Override
    public List<T> findAll (Specification<T> spec, Sort sort) {
        return getRepository().findAll(spec, sort);
    }

    @Override
    public long count (Specification<T> spec) {
        return getRepository().count(spec);
    }

    @Override
    public Usuario getUsuarioLogado() throws BusinessException {
        LawyerAuthenticationToken token = (LawyerAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (token.getUsuario() == null || StringUtils.isBlank(token.getUsuario().getUid())) {
            throw new BusinessException("Usuario nao encontrado na autenticação.");
        }
        return token.getUsuario();
    }

    @Override
    public LawyerAuthenticationToken getCredenciais() throws BusinessException {
        LawyerAuthenticationToken token = (LawyerAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (token == null || token.getCredentials() == null) {
            throw new BusinessException("Usuario nao encontrado na autenticação.");
        }
        return token;
    }

}