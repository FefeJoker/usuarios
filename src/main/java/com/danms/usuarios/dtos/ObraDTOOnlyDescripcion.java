package com.danms.usuarios.dtos;

import com.danms.usuarios.model.Obra;

public class ObraDTOOnlyDescripcion {
    private Integer id;
    private String descripcion;

    public ObraDTOOnlyDescripcion(Obra obra){
        this.id = obra.getId();
        this.descripcion = obra.getDescripcion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
