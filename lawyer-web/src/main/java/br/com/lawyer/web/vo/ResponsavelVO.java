package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class ResponsavelVO extends BaseVO<Responsavel> {

    private PessoaVO pessoa;

    private SetorVO setor;

    public ResponsavelVO () {

    }

    public ResponsavelVO(Responsavel responsavel) {
        super(responsavel);
    }

    public PessoaVO getPessoa () {
        return pessoa;
    }

    public void setPessoa (PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

    public SetorVO getSetor () {
        return setor;
    }

    public void setSetor (SetorVO setor) {
        this.setor = setor;
    }
}
