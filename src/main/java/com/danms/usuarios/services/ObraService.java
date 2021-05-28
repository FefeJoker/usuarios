package com.danms.usuarios.services;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Obra;
import com.danms.usuarios.repositories.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    ObraRepository obraRepository;

    @Autowired
    ClienteService clienteService;

    public Obra saveNewObra(Obra obra){
        Cliente cliente = clienteService.getClienteById(obra.getCliente().getId()).orElse(null);
        if(cliente != null){
            obraRepository.save(obra);
            cliente.addObra(obra);
            clienteService.updateCliente(cliente);
            return obra;
        }
        return null;
    }

    public Obra saveObraNewCliente(Obra obra){
        return obraRepository.save(obra);
    }

    public Optional<Obra> getObraById(Integer id){
        return Optional.of(obraRepository.getOne(id));
    }

    public void updateObra(Obra obra){
        if(getObraById(obra.getId()).isPresent())
            obraRepository.updateObra(obra.getDescripcion(), obra.getDireccion(),
                    obra.getLatitud(), obra.getLongitud(), obra.getSuperficie(), obra.getId());
    }

    public List<Obra> getObrasByClienteAndTipo(Integer cliente, Integer tipo){
        return obraRepository.getObrasByClienteAndTipo(cliente, tipo);
    }

    public List<Obra> getObrasByCliente(Integer cliente){
        Cliente cliente1 = clienteService.getClienteById(cliente).get();
        return obraRepository.getObrasByCliente(cliente1);
    }

    public List<Obra> getObrasByTipo(Integer tipo){
        return obraRepository.getObrasByTipo(tipo);
    }
}
