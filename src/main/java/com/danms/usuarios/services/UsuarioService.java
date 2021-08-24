package com.danms.usuarios.services;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Usuario;
import com.danms.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ClienteService clienteService;

    public Cliente login(Usuario usu){
        Usuario result = usuarioRepository.findUsuarioByUsernameAndPassword(usu.getUsername(), usu.getPassword())
                .orElse(null);
        if(result != null){
            return clienteService.getClienteByUsuario(result);
        }
        return null;
    }
}
