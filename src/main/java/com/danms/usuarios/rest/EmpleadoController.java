package com.danms.usuarios.rest;

/*import com.danms.usuarios.model.Empleado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    private static final List<Empleado> listaEmpleados = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado empleado){
        empleado.setId(ID_GEN++);
        listaEmpleados.add(empleado);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@RequestBody Empleado empleado, @PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaEmpleados.size())
                .filter(i -> listaEmpleados.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.set(indexOpt.getAsInt(), empleado);
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaEmpleados.size())
                .filter(i -> listaEmpleados.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> all(@RequestParam(name="mail", required = false) String mail){
        List<Empleado> respuesta;

        if(mail == null) respuesta = listaEmpleados;
        else    respuesta = listaEmpleados.stream()
                .filter(c -> c.getMail().equals(mail))
                .collect(Collectors.toList());

        if(respuesta.isEmpty()) return ResponseEntity.notFound().build();
        else    return ResponseEntity.ok(respuesta);
    }
}*/
