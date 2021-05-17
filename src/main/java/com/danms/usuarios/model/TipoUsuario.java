package com.danms.usuarios.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoUsuario {
    @Id
    private Integer id;
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
