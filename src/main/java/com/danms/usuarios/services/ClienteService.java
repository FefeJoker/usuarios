package com.danms.usuarios.services;

import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.model.Usuario;
import com.danms.usuarios.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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

        clienteRepository.save(nuevo);

        nuevo.getObras().stream().forEach(o -> {
            o.setCliente(nuevo);
            obraService.saveObraNewCliente(o);
        });

        return nuevo;
    }

    public Optional<Cliente> getClienteById(Integer id){
        return clienteRepository.getClienteByIdAndFechaBajaIsNull(id);
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

    public Cliente getClienteByUsuario(Usuario usuario){
        return clienteRepository.getClienteByUsuario(usuario).orElse(null);
    }

    public void updateCliente(Cliente cliente){
        if(getClienteById(cliente.getId()).isPresent())
            clienteRepository.updateCliente(cliente.getCuit(), cliente.getFechaBaja(), cliente.getHabilitadoOnline(),
                    cliente.getMail(), cliente.getMaxCuentaCorriente(), cliente.getRazonSocial(), cliente.getId());
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
