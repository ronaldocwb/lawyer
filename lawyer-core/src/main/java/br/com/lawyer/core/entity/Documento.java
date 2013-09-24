package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Documento implements Serializable {

	private static final long serialVersionUID = 9020556663256241017L;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoDocumento tipo;
	private String identificacao;
	
}
