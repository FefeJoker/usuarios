package com.danms.usuarios.repositories;

import com.danms.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findUsuarioByUsernameAndPassword(String username, String password);
}
