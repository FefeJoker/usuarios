package com.danms.usuarios.rest;

import com.danms.usuarios.dtos.ObraDTO;
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
    public ResponseEntity<ObraDTO> create(@RequestBody Obra obra){
        obra.setId(null);
        if(obra.getCliente() == null || obra.getCliente().getId() == null){
            return ResponseEntity.notFound().build();
        }

        Obra resultado = obraService.saveNewObra(obra);

        if(resultado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ObraDTO(resultado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraDTO> update(@RequestBody Obra obra, @PathVariable Integer id){
        Optional<Obra> obraOptional = obraService.getObraById(id);

        if(obraOptional.isPresent()){
            obra.setId(id);
            obraService.updateObra(obra);
            return ResponseEntity.ok(new ObraDTO(obra));
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
    public ResponseEntity<List<ObraDTO>> all(@RequestParam(name="idCliente", required = false) Integer idCliente,
                                          @RequestParam(name="tipoObra", required = false) Integer idTipoObra){
        List<Obra> listaObras;

        if(idCliente != null && idTipoObra != null) listaObras =
                obraService.getObrasByClienteAndTipo(idCliente, idTipoObra);
        else if(idCliente != null) listaObras = obraService.getObrasByCliente(idCliente);
        else if(idTipoObra != null) listaObras = obraService.getObrasByTipo(idTipoObra);
        else    return ResponseEntity.notFound().build();
        List<ObraDTO> respuesta = listaObras.stream().map(o -> new ObraDTO(o)).collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }
}
