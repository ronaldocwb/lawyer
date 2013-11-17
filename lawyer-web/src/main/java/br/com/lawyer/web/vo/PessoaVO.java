package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deividi
 * @since 28/09/2013
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
public class PessoaVO extends BaseVO<Pessoa>{

    private String uid;

    private String nome;

    private DocumentoVO documento;

    private EmpresaVO empresa;

    private String email;

    private List<TelefoneVO> telefones;

    private List<EnderecoVO> enderecos;

    private List<EmailVO> emails;

    public PessoaVO(Pessoa pessoa) {
        super(pessoa);
    }

    public PessoaVO () {

    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public DocumentoVO getDocumento () {
        return documento;
    }

    public void setDocumento (DocumentoVO documento) {
        this.documento = documento;
    }

    public EmpresaVO getEmpresa () {
        return empresa;
    }

    public void setEmpresa (EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public List<TelefoneVO> getTelefones () {
        return telefones;
    }

    public void setTelefones (List<TelefoneVO> telefones) {
        if (telefones == null) {
            return;
        }
        List<TelefoneVO> empty = new ArrayList<>();
        for (TelefoneVO vo : telefones) {

            if (vo == null|| vo.getNumero() == null) {
                empty.add(vo);
                continue;
            }
        }
        telefones.removeAll(empty);
        this.telefones = telefones;
    }
    public List<EnderecoVO> getEnderecos () {
        return enderecos;
    }

    public void setEnderecos (List<EnderecoVO> enderecos) {
        if (enderecos == null) {
            return;
        }
        List<EnderecoVO> empty = new ArrayList<>();
        for (EnderecoVO vo : enderecos) {

            if (vo == null) {
                empty.add(vo);
                continue;
            }
        }
        enderecos.removeAll(empty);
        this.enderecos = enderecos;
    }


    public List<EmailVO> getEmails () {
        return emails;
    }

    public void setEmails (List<EmailVO> emails) {
        if (emails == null) {
            return;
        }
        List<EmailVO> empty = new ArrayList<>();
        for (EmailVO vo : emails) {

            if (vo == null || vo.getValue() == null) {
                empty.add(vo);
                continue;
            }
        }
        emails.removeAll(empty);
        this.emails = emails;
    }

}
