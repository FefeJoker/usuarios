package com.danms.usuarios.repositories;

import com.danms.usuarios.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public List<Cliente> getClientesByRazonSocialAndFechaBajaIsNull(String razonSocial);
    public Cliente getClienteByCuitAndFechaBajaIsNull(String cuit);
    public Cliente getClienteByIdAndFechaBajaIsNull(Integer id);
}
