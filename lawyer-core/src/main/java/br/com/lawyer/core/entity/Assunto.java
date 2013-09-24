package br.com.lawyer.core.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.apache.commons.lang.StringUtils;

import br.com.lawyer.core.base.IUID;

public class Assunto implements IUID<String> {
	
	@Id
    private String uid;
	private Empresa cliente;
	private String nome;
	private String descricao;
	private Periodo duracao;
	private AreaAtuacao areaAtuacao;
	private Advogado responsavel;
	private TipoAcesso tipoAcesso;
	//private List<Grupo> grupoAcesso;
	private String localizacao; //questionar oq é
	private Contrato contrato;
	
	//contencioso
	private String numeroProcesso;
	private Date dataAutuacao;
	private Orgao orgaoJulgador;
	private BigDecimal valorCausa;
	private Pessoa autor;
	private Pessoa reu;
	private List<Advogado> advogadosEnvolvidos;
	private List<Assunto> assuntosRelacionados;
	
	@Enumerated
	private TipoProcesso tipoProcesso;
	
	
	@PrePersist
    private void generateUuid() {
        if (StringUtils.isBlank(this.uid)) {
            this.uid = UUID.randomUUID().toString();
        }
    }
	
	public void setUid (String uuid) {
        this.uid = uuid;
    }

    public String getUid () {
        return uid;
    }
	
}
