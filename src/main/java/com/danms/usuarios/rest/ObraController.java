package com.danms.usuarios.rest;

import com.danms.usuarios.model.Obra;
import com.danms.usuarios.services.ObraService;
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
@RequestMapping("/api/obra")
public class ObraController {

    @Autowired
    ObraService obraService;

    @PostMapping
    public ResponseEntity<Obra> create(@RequestBody Obra obra){
        obra.setId(null);
        if(obra.getCliente() == null || obra.getCliente().getId() == null){
            return ResponseEntity.notFound().build();
        }

        Obra resultado = obraService.saveNewObra(obra);

        if(resultado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> update(@RequestBody Obra obra, @PathVariable Integer id){
        Optional<Obra> obraOptional = obraService.getObraById(id);

        if(obraOptional.isPresent()){
            obra.setId(id);
            obraService.updateObra(obra);
            return ResponseEntity.ok(obra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Obra> delete(@PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaObras.size())
                .filter(i -> listaObras.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaObras.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping
    public ResponseEntity<List<Obra>> all(@RequestParam(name="idCliente", required = false) Integer idCliente,
                                          @RequestParam(name="tipoObra", required = false) Integer idTipoObra){
        List<Obra> respuesta;

        if(idCliente != null && idTipoObra != null) respuesta =
                obraService.getObrasByClienteAndTipo(idCliente, idTipoObra);
        else if(idCliente != null) respuesta = obraService.getObrasByCliente(idCliente);
        else if(idTipoObra != null) respuesta = obraService.getObrasByTipo(idTipoObra);
        else    return ResponseEntity.notFound().build();
        return ResponseEntity.ok(respuesta);
    }
}
