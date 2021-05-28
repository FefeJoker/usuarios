package com.danms.usuarios.repositories;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Obra;
import com.danms.usuarios.model.TipoObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
    public List<Obra> getObrasByClienteAndTipo(Integer cliente, Integer tipo);
    public List<Obra> getObrasByCliente(Cliente cliente);
    public List<Obra> getObrasByTipo(Integer tipo);

    @Transactional
    @Modifying
    @Query(value = "UPDATE obra SET descripcion = ?1, " +
            "direccion = ?2, " +
            "latitud = CAST (CAST (?3 AS VARCHAR) AS REAL), " +
            "longitud = CAST (CAST (?4 AS VARCHAR) AS REAL), " +
            "superficie = CAST (CAST (?5 AS VARCHAR) AS INTEGER) " +
            "WHERE id = ?6", nativeQuery = true)
    public void updateObra(String descripcion, String direccion, Float latitud,
                           Float longitud, Integer superficie, Integer id);
}
