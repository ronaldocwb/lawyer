package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.common.Periodo;
import br.com.lawyer.core.entity.enumerated.TipoAcesso;
import br.com.lawyer.core.entity.enumerated.TipoProcesso;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Assunto extends AbstractBaseEntity {

    @ManyToOne
    private Empresa cliente;

    @Column (length = 255)
    private String nome;

    @Column (length = 5000)
    private String descricao;

    @Embedded
    private Periodo duracao;

    @ManyToOne
    private AreaAtuacao areaAtuacao;


    @ManyToOne
    @JoinColumn (name = "uuid_adv_responsavel")
    private Advogado responsavel;

    @Enumerated (EnumType.ORDINAL)
    private TipoAcesso tipoAcesso;

    //private List<Grupo> grupoAcesso;

    @Column (length = 250)
    private String localizacao; //questionar oq é

    @ManyToOne
    private Contrato contrato;

    //contencioso
    @Column (length = 20)
    private String numeroProcesso;

    @Temporal (TemporalType.DATE)
    private Date dataAutuacao;

    @ManyToOne
    private Orgao orgaoJulgador;

    private BigDecimal valorCausa;

    @ManyToOne
    @JoinColumn (name = "uuid_pessoa_autor")
    private Pessoa autor;

    @ManyToOne
    @JoinColumn (name = "uuid_pessoa_reu")
    private Pessoa reu;

    @OneToMany
    @JoinTable (name = "assunto_adv_envolvido")
    private List<Advogado> advogadosEnvolvidos;

    @OneToMany
    @JoinTable (name = "assunto_relacionado")
    private List<Assunto> assuntosRelacionados;

    @Enumerated (EnumType.ORDINAL)
    private TipoProcesso tipoProcesso;

    public Empresa getCliente () {
        return cliente;
    }

    public void setCliente (Empresa cliente) {
        this.cliente = cliente;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public Periodo getDuracao () {
        return duracao;
    }

    public void setDuracao (Periodo duracao) {
        this.duracao = duracao;
    }

    public AreaAtuacao getAreaAtuacao () {
        return areaAtuacao;
    }

    public void setAreaAtuacao (AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Advogado getResponsavel () {
        return responsavel;
    }

    public void setResponsavel (Advogado responsavel) {
        this.responsavel = responsavel;
    }

    public TipoAcesso getTipoAcesso () {
        return tipoAcesso;
    }

    public void setTipoAcesso (TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public String getLocalizacao () {
        return localizacao;
    }

    public void setLocalizacao (String localizacao) {
        this.localizacao = localizacao;
    }

    public Contrato getContrato () {
        return contrato;
    }

    public void setContrato (Contrato contrato) {
        this.contrato = contrato;
    }

    public String getNumeroProcesso () {
        return numeroProcesso;
    }

    public void setNumeroProcesso (String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public Date getDataAutuacao () {
        return dataAutuacao;
    }

    public void setDataAutuacao (Date dataAutuacao) {
        this.dataAutuacao = dataAutuacao;
    }

    public Orgao getOrgaoJulgador () {
        return orgaoJulgador;
    }

    public void setOrgaoJulgador (Orgao orgaoJulgador) {
        this.orgaoJulgador = orgaoJulgador;
    }

    public BigDecimal getValorCausa () {
        return valorCausa;
    }

    public void setValorCausa (BigDecimal valorCausa) {
        this.valorCausa = valorCausa;
    }

    public Pessoa getAutor () {
        return autor;
    }

    public void setAutor (Pessoa autor) {
        this.autor = autor;
    }

    public Pessoa getReu () {
        return reu;
    }

    public void setReu (Pessoa reu) {
        this.reu = reu;
    }

    public TipoProcesso getTipoProcesso () {
        return tipoProcesso;
    }

    public void setTipoProcesso (TipoProcesso tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public List<Advogado> getAdvogadosEnvolvidos () {
        return advogadosEnvolvidos;
    }

    public void addAdvogadoEnvolvido (Advogado a) {
        if (advogadosEnvolvidos == null) {
            advogadosEnvolvidos = new ArrayList<Advogado>();
        }

        advogadosEnvolvidos.add(a);
    }

    public void removeAdvogadoEnvolvido (Advogado a) {
        if (advogadosEnvolvidos == null) {
            return;
        }

        advogadosEnvolvidos.remove(a);
    }

    public List<Assunto> getAssuntosRelacionados () {
        return assuntosRelacionados;
    }

    public void addAssuntoRelacionado (Assunto assunto) {
        if (assuntosRelacionados == null) {
            assuntosRelacionados = new ArrayList<Assunto>();
        }

        assuntosRelacionados.add(assunto);
    }

    public void removeAssuntoRelacionado (Assunto assunto) {
        if (assuntosRelacionados == null) {
            return;
        }

        assuntosRelacionados.remove(assunto);
    }
}
