package br.com.lawyer.core.repository.specifications;

import br.com.lawyer.core.entity.Responsavel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Deividi
 * @since 23/10/2013
 */
public class ResponsavelSpecification {

    public static final String PESSOA = "pessoa.nome";
    public static final String EMPRESA = "empresa.nomeFantasia";

    public static Specification<Responsavel> queryResponsavelPorNomePessoaLike (final String busca) {
        return new Specification<Responsavel>() {
            public Predicate toPredicate(Root<Responsavel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal((root.get("pessoa")).get("nome"), busca);

            }
        };
    }

    public static Specification<Responsavel> queryResponsavelPorNomeEmpresaLike (final String busca) {
        return new Specification<Responsavel>() {
            public Predicate toPredicate(Root<Responsavel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal((root.get("empresa")).get("nomeFantasia"), busca);

            }
        };
    }
}
