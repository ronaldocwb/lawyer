package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.TipoRepeticao;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * @author Deividi
 * @since 08/12/2013
 */
@Entity
public class Evento extends AbstractBaseEntity {

    private Date dataInicio;

    private Date dataFim;

    private String titulo;

    private String detalhes;

    private String localizacao;

    private Boolean diaInteiro;

    @ManyToOne
    private Agenda owner;

    @ManyToMany
    private List<Agenda> participantes;

    @ManyToOne
    private Assunto assunto;

    @ManyToMany
    private List<Contato> contatos;

    @Enumerated
    private TipoRepeticao repeticao;

    public String getLocalizacao () {
        return localizacao;
    }

    public void setLocalizacao (String localizacao) {
        this.localizacao = localizacao;
    }

    public Assunto getAssunto () {
        return assunto;
    }

    public void setAssunto (Assunto assunto) {
        this.assunto = assunto;
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

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo (String titulo) {
        this.titulo = titulo;
    }

    public String getDetalhes () {
        return detalhes;
    }

    public void setDetalhes (String detalhes) {
        this.detalhes = detalhes;
    }

    public Boolean getDiaInteiro () {
        return diaInteiro;
    }

    public void setDiaInteiro (Boolean diaInteiro) {
        this.diaInteiro = diaInteiro;
    }

    public List<Contato> getContatos () {
        return contatos;
    }

    public void setContatos (List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Agenda> getParticipantes () {
        return participantes;
    }

    public void setParticipantes (List<Agenda> convidados) {
        this.participantes = convidados;
    }

    public Agenda getOwner () {
        return owner;
    }

    public void setOwner (Agenda owner) {
        this.owner = owner;
    }

    public TipoRepeticao getRepeticao () {
        return repeticao;
    }

    public void setRepeticao (TipoRepeticao repeticao) {
        this.repeticao = repeticao;
    }
}
