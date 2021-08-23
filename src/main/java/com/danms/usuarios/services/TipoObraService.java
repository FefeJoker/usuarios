package com.danms.usuarios.services;

import com.danms.usuarios.model.TipoObra;
import com.danms.usuarios.repositories.TipoObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class TipoObraService {
    @Autowired
    TipoObraRepository tipoObraRepository;

    @GetMapping
    public List<TipoObra> getAll(){
        return tipoObraRepository.findAll();
    }
}
