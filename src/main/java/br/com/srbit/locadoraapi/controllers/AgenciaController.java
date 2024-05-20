package br.com.srbit.locadoraapi.controllers;

import br.com.srbit.locadoraapi.dto.AgenciaRecordDTO;
import br.com.srbit.locadoraapi.models.AgenciaModel;
import br.com.srbit.locadoraapi.services.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agencia")
@CrossOrigin
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
    public ResponseEntity<Page<AgenciaModel>> findAll(@PageableDefault(page = 0, size=10, sort = "nome", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
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
