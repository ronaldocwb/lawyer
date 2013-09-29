package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.web.base.BaseVO;

import java.util.List;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class PessoaVO extends BaseVO<Pessoa>{

    private String uid;

    private String nome;

    private DocumentoVO documento;

    private EmpresaVO empresa;

    private String email;

    private List<TelefoneVO> telefones;

    private List<EnderecoVO> enderecos;

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
        this.telefones = telefones;
    }

    public List<EnderecoVO> getEnderecos () {
        return enderecos;
    }

    public void setEnderecos (List<EnderecoVO> enderecos) {
        this.enderecos = enderecos;
    }
}
