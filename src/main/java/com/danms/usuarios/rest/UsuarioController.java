package com.danms.usuarios.rest;

import com.danms.usuarios.dtos.ClienteDTO;
import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Usuario;
import com.danms.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://104.198.16.92", "http://190.190.173.85", "http://190.3.50.121"})
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping(path = "/login")
    public ResponseEntity<ClienteDTO> login(@RequestBody Usuario usuario){
        Cliente result = usuarioService.login(usuario);
        if (result != null){
            ClienteDTO clienteDTO = new ClienteDTO(result);
            return ResponseEntity.ok(clienteDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
