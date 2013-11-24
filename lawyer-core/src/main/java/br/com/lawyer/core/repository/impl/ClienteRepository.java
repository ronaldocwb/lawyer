package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.*;
import br.com.lawyer.core.repository.IClienteRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

/**
 * @author Deividi Cavarzan
 * @since 20/09/2013
 */
@Repository
public class ClienteRepository extends JPABaseRepository<String, Cliente> implements IClienteRepository {

    @Autowired
    public ClienteRepository (EntityManager em) {
        super(Cliente.class, em);
    }


    public Page<Cliente> findClientes (String query, Pageable pageRequest) {
        QCliente cliente = QCliente.cliente;
        QPessoa pessoa = QPessoa.pessoa;
        QEmpresa empresa = QEmpresa.empresa;

        BooleanBuilder builder = new BooleanBuilder();
        builder.or(pessoa.nome.containsIgnoreCase(query));
        builder.or(empresa.nomeFantasia.containsIgnoreCase(query));

        JPQLQuery jpqlQuery = querydsl.createQuery(cliente)
                .leftJoin(cliente.pessoa, pessoa)
                .leftJoin(cliente.empresa, empresa)
                .where(builder);

        JPQLQuery pagedQuery =  querydsl.applyPagination(pageRequest, jpqlQuery);
        long total = jpqlQuery.count();

        List<Cliente> content = total > pageRequest.getOffset() ? pagedQuery.list(path) : Collections.<Cliente> emptyList();

        return new PageImpl<>(content, pageRequest, total);
    }

    @Override
    public void remover (String uid, Class<?> klazz) {
        JPADeleteClause clause = new JPADeleteClause(getEntityManager(), path);
        QCliente cliente = QCliente.cliente;

        if (klazz.equals(Pessoa.class)) {
            clause.where(cliente.pessoa.uid.eq(uid));
        } else if (klazz.equals(Empresa.class)) {
            clause.where(cliente.empresa.uid.eq(uid));
        }

        clause.execute();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Cliente salvar (Cliente cliente) {
        getEntityManager().persist(cliente);
        getEntityManager().flush();
        return cliente;
    }
}
