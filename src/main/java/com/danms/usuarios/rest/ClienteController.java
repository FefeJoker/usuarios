package com.danms.usuarios.rest;

import com.danms.usuarios.dtos.ClienteDTO;
import com.danms.usuarios.model.Cliente;
import com.danms.usuarios.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteDTO> clientePorId(@PathVariable Integer id){

        Optional<Cliente> c = clienteService.getClienteById(id);

        if(c.isPresent())  return ResponseEntity.ok(new ClienteDTO(c.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> todos(@RequestParam(name="razonSocial", required = false) String razonSocial){
        List<Cliente> listaClientes;

        if(razonSocial == null) listaClientes = clienteService.getAllCliente();
        else    listaClientes = clienteService.getClienteByRazonSocial(razonSocial);

        if(listaClientes.isEmpty()) return ResponseEntity.notFound().build();
        else{
            List<ClienteDTO> respuesta = listaClientes.stream().
                    map(c -> new ClienteDTO(c)).collect(Collectors.toList());
            return ResponseEntity.ok(respuesta);
        }
    }

    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<ClienteDTO> getClienteByCuit(@PathVariable String cuit){
        Optional<Cliente> clienteOptional= clienteService.getClienteByCuit(cuit);

        if(clienteOptional.isPresent())  return ResponseEntity
                .ok(new ClienteDTO(clienteOptional.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody Cliente nuevo){
       if(nuevo.getObras().isEmpty() || nuevo.getObras().get(0).getTipo() == null)
            return ResponseEntity.badRequest().build();

        //
        nuevo.setId(null);
        Cliente guardado = clienteService.saveNewCliente(nuevo);
        //

        return ResponseEntity.ok(new ClienteDTO(guardado));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){
        Optional<Cliente> clienteOptional= clienteService.getClienteById(id);

        if(clienteOptional.isPresent()){
            nuevo.setId(id);
            clienteService.updateCliente(nuevo);
            return ResponseEntity.ok(new ClienteDTO(nuevo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClienteDTO> borrar(@PathVariable Integer id){
        Optional<Cliente> clienteOptional= clienteService.getClienteById(id);

        if(clienteOptional.isPresent()){
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
