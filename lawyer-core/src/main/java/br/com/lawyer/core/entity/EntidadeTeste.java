package br.com.lawyer.core.entity;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class EntidadeTeste implements Serializable {

    @Id
    private String uuid;

    public String getUuid() {
        if (StringUtils.isBlank(this.uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
