package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepositoryImpl;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.entity.QCliente;
import br.com.lawyer.core.entity.QEmpresa;
import br.com.lawyer.core.entity.QPessoa;
import br.com.lawyer.core.repository.ClienteRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 25/11/2013
 */
@Repository
public class ClienteRepositoryImpl extends JPABaseRepositoryImpl<String, Cliente> implements ClienteRepository {

    @Override
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

        return findAll(jpqlQuery, pageRequest);

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

}
