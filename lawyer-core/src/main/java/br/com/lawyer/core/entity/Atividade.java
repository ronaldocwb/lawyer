package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.ValorHoraAtividade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIgnoreProperties (ignoreUnknown = true)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
public class Atividade extends AbstractBaseEntity {

    @ManyToOne
    @JsonIgnoreProperties({"advocacia", "pessoa", "permissoes" })
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"cliente", "descricao", "duracao", "areaAtuacao", "responsavel", "contrato", "autor", "reu", "advogadosEnvolvidos", "assuntosRelacionados" })
    private Assunto assunto;

    @Temporal (TemporalType.DATE)
    private Date dataReferencia;

    @Column(length = 255)
    private String descricao;

    @Column(length = 2047)
    private String observacao;

    private Long duracao;

    private boolean ativo;

    @Temporal (TemporalType.DATE)
    private Date dataInicio;

    @Temporal (TemporalType.DATE)
    private Date dataFim;

    @Column(scale = 2, precision = 10)
    private BigDecimal valorHora;

    @Enumerated(value = EnumType.ORDINAL)
    private ValorHoraAtividade valorHoraAtividade;

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
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

    public BigDecimal getValorHora () {
        return valorHora;
    }

    public void setValorHora (BigDecimal valorTotal) {
        this.valorHora = valorTotal;
    }

    public Assunto getAssunto () {
        return assunto;
    }

    public void setAssunto (Assunto assunto) {
        this.assunto = assunto;
    }

    public ValorHoraAtividade getValorHoraAtividade () {
        return valorHoraAtividade;
    }

    public void setValorHoraAtividade (ValorHoraAtividade valorHoraAtividade) {
        this.valorHoraAtividade = valorHoraAtividade;
    }
}
