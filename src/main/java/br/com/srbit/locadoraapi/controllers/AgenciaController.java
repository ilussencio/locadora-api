package br.com.srbit.locadoraapi.controllers;

import br.com.srbit.locadoraapi.dto.AgenciaRecordDTO;
import br.com.srbit.locadoraapi.models.AgenciaModel;
import br.com.srbit.locadoraapi.services.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {
    private final AgenciaService service;

    public AgenciaController(AgenciaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgenciaModel> save(@RequestBody @Valid AgenciaRecordDTO agenciaRecordDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(agenciaRecordDTO));
    }

    @GetMapping
    public ResponseEntity<List<AgenciaModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciaModel> findOne(@PathVariable(value = "id") UUID id) throws Exception{
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenciaModel> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid AgenciaRecordDTO agenciaRecordDTO) throws Exception {
        return ResponseEntity.ok().body(service.update(agenciaRecordDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) throws Exception{
        service.delete(id);
        return ResponseEntity.ok().body("Agencia deletada com sucesso!");
    }

}
