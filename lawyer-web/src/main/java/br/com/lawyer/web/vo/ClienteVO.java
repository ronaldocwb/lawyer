package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public class ClienteVO extends BaseVO<Cliente> {

    private String uid;

    private EmpresaVO empresa;

    private PessoaVO pessoa;

    public ClienteVO() {}

    public ClienteVO(Cliente cliente) {
        super(cliente);
    }

    public ClienteVO(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

    public ClienteVO(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public EmpresaVO getEmpresa () {
        return empresa;
    }

    public void setEmpresa (EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public PessoaVO getPessoa () {
        return pessoa;
    }

    public void setPessoa (PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }
}
