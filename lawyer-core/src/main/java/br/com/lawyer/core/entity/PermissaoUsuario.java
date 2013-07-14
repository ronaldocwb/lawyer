package br.com.lawyer.core.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Enumeration;

@Entity
public class PermissaoUsuario implements Serializable {

    @Id
    private String uuid;

    @Enumerated(value = EnumType.ORDINAL)
    private Permissao permissao;

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
