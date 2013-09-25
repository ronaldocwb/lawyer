package br.com.lawyer.core.entity.base;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.lawyer.core.base.IUID;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable, IUID<String> {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;

	public AbstractBaseEntity() {
		this.uid = UUID.randomUUID().toString();
	}
	
	public String getUid() {
		return uid;
	}

	@Override
	public int hashCode() {
		return getUid().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractBaseEntity)) {
			return false;
		}
		AbstractBaseEntity other = (AbstractBaseEntity) obj;
		return getUid().equals(other.getUid());
	}
}
