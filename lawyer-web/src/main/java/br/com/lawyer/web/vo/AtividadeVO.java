package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Deividi
 * @since 11/11/2013
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtividadeVO extends BaseVO<Atividade> {

    private UsuarioVO usuario;

    private AssuntoVO assunto;

    private Date dataReferencia;

    private String descricao;

    private String observacao;

    private Long duracao;

    private boolean ativo;

    private Date dataInicio;

    private Date dataFim;

    private BigDecimal valorTotal;

    public AtividadeVO() {}

    public AtividadeVO(Atividade atividade) {
        super(atividade);
    }

    public UsuarioVO getUsuario () {
        return usuario;
    }

    public void setUsuario (UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public AssuntoVO getAssunto () {
        return assunto;
    }

    public void setAssunto (AssuntoVO assunto) {
        this.assunto = assunto;
    }

    public Date getDataReferencia () {
        return dataReferencia;
    }

    public void setDataReferencia (Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao () {
        return observacao;
    }

    public void setObservacao (String observacao) {
        this.observacao = observacao;
    }

    public Long getDuracao () {
        return duracao;
    }

    public void setDuracao (Long duracao) {
        this.duracao = duracao;
    }

    public boolean isAtivo () {
        return ativo;
    }

    public void setAtivo (boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataInicio () {
        return dataInicio;
    }

    public void setDataInicio (Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim () {
        return dataFim;
    }

    public void setDataFim (Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorTotal () {
        return valorTotal;
    }

    public void setValorTotal (BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
