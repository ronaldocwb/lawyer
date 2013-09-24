package br.com.lawyer.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Periodo implements Serializable{
	
	private static final long serialVersionUID = -2708153981864691785L;
	
	private Date inicio;
	private Date fim;
	
	public Periodo() {
	}
	
	public Periodo(Date inicio, Date fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public Date getInicio() {
		return inicio;
	}
	public Date getFim() {
		return fim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fim == null) ? 0 : fim.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		if (fim == null) {
			if (other.fim != null)
				return false;
		} else if (!fim.equals(other.fim))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		return true;
	}
}
