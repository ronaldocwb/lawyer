package br.com.lawyer.core.entity;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Usuario implements Serializable {

    @Id
    private String uuid;

    private String email;

    private String senhaCriptografada;

    @PrePersist
    private void generateUuid() {
        if (StringUtils.isBlank(this.uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
