package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public class ClienteVO extends BaseVO<Cliente> {

    private String nome;

    public ClienteVO (Cliente cliente) {
        super(cliente);
    }

    public ClienteVO () { }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
