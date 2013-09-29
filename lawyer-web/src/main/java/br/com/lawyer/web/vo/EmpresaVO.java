package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.web.base.BaseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public class EmpresaVO extends BaseVO<Empresa> {

    private String uid;

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private List<TelefoneVO> telefones;

    private List<EnderecoVO> enderecos;

    private List<ResponsavelVO> responsaveis;

    public EmpresaVO(Empresa empresa) {
        super(empresa);
    }

    public EmpresaVO () { }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getRazaoSocial () {
        return razaoSocial;
    }

    public void setRazaoSocial (String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia () {
        return nomeFantasia;
    }

    public void setNomeFantasia (String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj () {
        return cnpj;
    }

    public void setCnpj (String cnpj) {
        this.cnpj = cnpj;
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

            if (vo == null || vo.getLogradouro() == null) {
                empty.add(vo);
                continue;
            }
        }
        enderecos.removeAll(empty);
        this.enderecos = enderecos;
    }

    public List<ResponsavelVO> getResponsaveis () {
        return responsaveis;
    }

    public void setResponsaveis (List<ResponsavelVO> responsaveis) {
        if (responsaveis == null) {
            return;
        }
        List<ResponsavelVO> empty = new ArrayList<>();
        for (ResponsavelVO vo : responsaveis) {

            if (vo == null|| vo.getPessoa() == null || vo.getPessoa().getUid() == null) {
                empty.add(vo);
                continue;
            }
            if (vo.getEmpresa() == null) {
                EmpresaVO e = new EmpresaVO();
                e.setUid(uid);
                vo.setEmpresa(e);
            }
        }
        responsaveis.removeAll(empty);
        this.responsaveis = responsaveis;
    }
}
