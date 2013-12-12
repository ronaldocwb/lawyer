package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Entity;
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

    @ManyToMany
    private List<Agenda> agendas;

    @ManyToOne
    private Assunto assunto;

//    private List<Advogado> advogados;


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

    public List<Agenda> getAgendas () {
        return agendas;
    }

    public void setAgendas (List<Agenda> agendas) {
        this.agendas = agendas;
    }
}
