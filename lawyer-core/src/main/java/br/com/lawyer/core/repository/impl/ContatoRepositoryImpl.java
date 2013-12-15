package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepositoryImpl;
import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.entity.QContato;
import br.com.lawyer.core.entity.QEmpresa;
import br.com.lawyer.core.entity.QPessoa;
import br.com.lawyer.core.entity.enumerated.TipoContato;
import br.com.lawyer.core.repository.ContatoRepository;
import br.com.lawyer.core.util.StringUtils;
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
public class ContatoRepositoryImpl extends JPABaseRepositoryImpl<String, Contato> implements ContatoRepository {

    @Override
    public Page<Contato> findClientes (String query, String tipo, Pageable pageRequest) {
        QContato contato = QContato.contato;
        QPessoa pessoa = QPessoa.pessoa;
        QEmpresa empresa = QEmpresa.empresa;

        BooleanBuilder builder = new BooleanBuilder();
        builder.or(pessoa.nome.containsIgnoreCase(query));
        builder.or(empresa.nomeFantasia.containsIgnoreCase(query));
        if (StringUtils.isNotBlank(tipo)) {
            builder.and(contato.tipoContato.eq(TipoContato.valueOf(tipo)));
        }

        JPQLQuery jpqlQuery = querydsl.createQuery(contato)
                .leftJoin(contato.pessoa, pessoa)
                .leftJoin(contato.empresa, empresa)
                .where(builder);

        return findAll(jpqlQuery, pageRequest);

    }

    @Override
    public void remover (String uid, Class<?> klazz) {
        JPADeleteClause clause = new JPADeleteClause(getEntityManager(), path);
        QContato cliente = QContato.contato;

        if (klazz.equals(Pessoa.class)) {
            clause.where(cliente.pessoa.uid.eq(uid));
        } else if (klazz.equals(Empresa.class)) {
            clause.where(cliente.empresa.uid.eq(uid));
        }

        clause.execute();
    }

}
