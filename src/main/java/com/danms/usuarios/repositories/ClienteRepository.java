package com.danms.usuarios.repositories;

import com.danms.usuarios.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public List<Cliente> getClientesByRazonSocialAndFechaBajaIsNull(String razonSocial);
    public Cliente getClienteByCuitAndFechaBajaIsNull(String cuit);
    public Optional<Cliente> getClienteByIdAndFechaBajaIsNull(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cliente SET cuit = ?1, " +
            "fecha_baja = CAST (CAST (?2 AS VARCHAR) AS TIMESTAMP), " +
            "habilitado_online = CAST (CAST (?3 AS VARCHAR) AS BOOLEAN), " +
            "mail = ?4, " +
            "max_cuenta_corriente = CAST (CAST (?5 AS VARCHAR) AS DOUBLE PRECISION), " +
            "razon_social = ?6 " +
            "WHERE id = ?7", nativeQuery = true)
    public void updateCliente(String cuit, Date fecha_baja, Boolean habilitado_online, String mail,
                           Double max_cuenta_corriente, String razon_social, Integer id);
}
