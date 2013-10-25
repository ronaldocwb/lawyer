package br.com.lawyer.core.entity.base;

import br.com.lawyer.core.base.IUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable, IUID<String> {
    private static final long serialVersionUID = 1L;

    @Id
    private String uid;

    public AbstractBaseEntity () { }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getUid () {
        return uid;
    }

    @PrePersist
    protected void generateUid() {
        if (this.uid == null) {
            this.uid = UUID.randomUUID().toString();
        }
    }

    @Override
    public int hashCode () {
        return getUid().hashCode();
    }

    @Override
    public boolean equals (Object obj) {
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
