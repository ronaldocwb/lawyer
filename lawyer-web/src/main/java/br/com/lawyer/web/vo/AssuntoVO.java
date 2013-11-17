package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.entity.Contrato;
import br.com.lawyer.core.entity.Orgao;
import br.com.lawyer.core.entity.common.Periodo;
import br.com.lawyer.core.entity.enumerated.TipoAcesso;
import br.com.lawyer.core.entity.enumerated.TipoProcesso;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@JsonIgnoreProperties (ignoreUnknown = true)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
public class AssuntoVO extends BaseVO<Assunto> {

    private String uid;

    private EmpresaVO cliente;

    private String nome;

    private String descricao;

    private Periodo duracao;

    private AreaAtuacaoVO areaAtuacao;

    private AdvogadoVO responsavel;

    private TipoAcesso tipoAcesso;

    private String localizacao;

    private Contrato contrato;

    private Date dataAutuacao;

    private Orgao orgaoJulgador;

    private BigDecimal valorCausa;

    private PessoaVO autor;

    private PessoaVO reu;

    private List<AdvogadoVO> advogadosEnvolvidos;

    private List<AssuntoVO> assuntosRelacionados;

    private TipoProcesso tipoProcesso;

    private String numeroProcesso;

    public AssuntoVO() {}

    public AssuntoVO(Assunto assunto) {
        super(assunto);
    }

    public String getNumeroProcesso () {
        return numeroProcesso;
    }

    public void setNumeroProcesso (String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public EmpresaVO getCliente () {
        return cliente;
    }

    public void setCliente (EmpresaVO cliente) {
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

    public AreaAtuacaoVO getAreaAtuacao () {
        return areaAtuacao;
    }

    public void setAreaAtuacao (AreaAtuacaoVO areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public AdvogadoVO getResponsavel () {
        return responsavel;
    }

    public void setResponsavel (AdvogadoVO responsavel) {
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

    public PessoaVO getAutor () {
        return autor;
    }

    public void setAutor (PessoaVO autor) {
        this.autor = autor;
    }

    public PessoaVO getReu () {
        return reu;
    }

    public void setReu (PessoaVO reu) {
        this.reu = reu;
    }

    public List<AdvogadoVO> getAdvogadosEnvolvidos () {
        return advogadosEnvolvidos;
    }

    public void setAdvogadosEnvolvidos (List<AdvogadoVO> advogadosEnvolvidos) {
        this.advogadosEnvolvidos = advogadosEnvolvidos;
    }

    public List<AssuntoVO> getAssuntosRelacionados () {
        return assuntosRelacionados;
    }

    public void setAssuntosRelacionados (List<AssuntoVO> assuntosRelacionados) {
        this.assuntosRelacionados = assuntosRelacionados;
    }

    public TipoProcesso getTipoProcesso () {
        return tipoProcesso;
    }

    public void setTipoProcesso (TipoProcesso tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }
}
