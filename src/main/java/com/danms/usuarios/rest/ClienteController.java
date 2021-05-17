package com.danms.usuarios.rest;

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
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id){

        Optional<Cliente> c = clienteService.getClienteById(id);
        return ResponseEntity.of(c);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> todos(@RequestParam(name="razonSocial", required = false) String razonSocial){
        List<Cliente> respuesta;

        if(razonSocial == null) respuesta = clienteService.getAllCliente();
        else    respuesta = clienteService.getClienteByRazonSocial(razonSocial);

        if(respuesta.isEmpty()) return ResponseEntity.notFound().build();
        else    return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<Cliente> getClienteByCuit(@PathVariable String cuit){
        Optional<Cliente> clienteOptional= clienteService.getClienteByCuit(cuit);

        return ResponseEntity.of(clienteOptional);
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente nuevo){
        //System.out.println(" crear cliente "+nuevo);
        if(nuevo.getObras().isEmpty() || nuevo.getObras().get(0).getTipo() == null)
            return ResponseEntity.badRequest().build();

        //
        nuevo.setId(null);
        Cliente guardado = clienteService.saveNewCliente(nuevo);
        //

        return ResponseEntity.ok(guardado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){
        Optional<Cliente> clienteOptional= clienteService.getClienteById(id);

        if(clienteOptional.isPresent()){
            nuevo.setId(id);
            clienteService.updateCliente(nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Cliente> borrar(@PathVariable Integer id){
        Optional<Cliente> clienteOptional= clienteService.getClienteById(id);

        if(clienteOptional.isPresent()){
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
