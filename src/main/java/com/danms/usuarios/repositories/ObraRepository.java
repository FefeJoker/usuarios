package com.danms.usuarios.repositories;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Obra;
import com.danms.usuarios.model.TipoObra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
    public List<Obra> getObrasByClienteAndTipo(Integer cliente, Integer tipo);
    public List<Obra> getObrasByCliente(Integer cliente);
    public List<Obra> getObrasByTipo(Integer tipo);
}
