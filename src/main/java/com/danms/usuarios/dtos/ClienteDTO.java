package com.danms.usuarios.dtos;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Obra;
import com.danms.usuarios.model.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

public class ClienteDTO {
    private Integer id;
    private String razonSocial;
    private String cuit;
    private String mail;
    private Double maxCuentaCorriente;
    private Boolean habilitadoOnline;
    private Usuario usuario;
    private Date fechaBaja;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.razonSocial = cliente.getRazonSocial();
        this.cuit = cliente.getCuit();
        this.mail = cliente.getMail();
        this.maxCuentaCorriente = cliente.getMaxCuentaCorriente();
        this.habilitadoOnline = cliente.getHabilitadoOnline();
        this.usuario = cliente.getUsuario();
        this.fechaBaja = cliente.getFechaBaja();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getMaxCuentaCorriente() {
        return maxCuentaCorriente;
    }

    public void setMaxCuentaCorriente(Double maxCuentaCorriente) {
        this.maxCuentaCorriente = maxCuentaCorriente;
    }

    public Boolean getHabilitadoOnline() {
        return habilitadoOnline;
    }

    public void setHabilitadoOnline(Boolean habilitadoOnline) {
        this.habilitadoOnline = habilitadoOnline;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
