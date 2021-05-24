package com.danms.usuarios.services;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Usuario;
import com.danms.usuarios.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ObraService obraService;

    public Cliente saveNewCliente(Cliente nuevo){
        nuevo.setHabilitadoOnline(false);
        nuevo.setUsuario(new Usuario(nuevo));

        nuevo.getObras().stream().forEach(o -> obraService.saveObraNewCliente(o));

        clienteRepository.save(nuevo);
        return nuevo;
    }

    public Optional<Cliente> getClienteById(Integer id){
        return Optional.of(clienteRepository.getClienteByIdAndFechaBajaIsNull(id));
    }

    public List<Cliente> getAllCliente(){
        return clienteRepository.findAll();
    }

    public List<Cliente> getClienteByRazonSocial(String razonSocial){
        return clienteRepository.getClientesByRazonSocialAndFechaBajaIsNull(razonSocial);
    }

    public Optional<Cliente> getClienteByCuit(String cuit){
        return Optional.of(clienteRepository.getClienteByCuitAndFechaBajaIsNull(cuit));
    }

    public void updateCliente(Cliente cliente){
        if(getClienteById(cliente.getId()).isPresent())
            clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id){
        Cliente cliente = getClienteById(id).orElse(null);
        if(cliente != null){
            if(cliente.getObras().isEmpty())
                clienteRepository.delete(cliente);
            else{
                cliente.setFechaBaja(new Date());
                updateCliente(cliente);
            }
        }
    }
}
