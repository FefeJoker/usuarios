package com.danms.usuarios.rest;

import com.danms.usuarios.model.TipoObra;
import com.danms.usuarios.services.TipoObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-obra")
public class TipoObraController {
    @Autowired
    TipoObraService tipoObraService;

    @GetMapping
    public ResponseEntity<List<TipoObra>> getAll(){
        return ResponseEntity.ok(tipoObraService.getAll());
    }
}
