package com.danms.usuarios.dtos;

import com.danms.usuarios.model.Obra;
import com.danms.usuarios.model.TipoObra;


public class ObraDTO {
    private Integer id;
    private String descripcion;
    private Float latitud;
    private Float longitud;
    private String direccion;
    private Integer superficie;
    private TipoObra tipo;

    public ObraDTO(Obra obra){
        this.id = obra.getId();
        this.descripcion = obra.getDescripcion();
        this.latitud = obra.getLatitud();
        this.longitud = obra.getLongitud();
        this.direccion = obra.getDireccion();
        this.superficie = obra.getSuperficie();
        this.tipo = obra.getTipo();
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

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public TipoObra getTipo() {
        return tipo;
    }

    public void setTipo(TipoObra tipo) {
        this.tipo = tipo;
    }
}
