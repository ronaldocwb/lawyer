package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Endereco;
import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class EnderecoVO extends BaseVO<Endereco> {

    private Municipio municipio;

    private String cep;

    private String numero;

    private String logradouro;

    private String complemento;

    private String bairro;

    public EnderecoVO(Endereco endereco) {
        super(endereco);
    }

    public EnderecoVO() {}

    public Municipio getMunicipio () {
        return municipio;
    }

    public void setMunicipio (Municipio municipio) {
        this.municipio = municipio;
    }

    public String getCep () {
        return cep;
    }

    public void setCep (String cep) {
        this.cep = cep;
    }

    public String getNumero () {
        return numero;
    }

    public void setNumero (String numero) {
        this.numero = numero;
    }

    public String getLogradouro () {
        return logradouro;
    }

    public void setLogradouro (String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento () {
        return complemento;
    }

    public void setComplemento (String complemento) {
        this.complemento = complemento;
    }

    public String getBairro () {
        return bairro;
    }

    public void setBairro (String bairro) {
        this.bairro = bairro;
    }
}
