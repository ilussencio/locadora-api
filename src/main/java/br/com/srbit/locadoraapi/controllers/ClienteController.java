package br.com.srbit.locadoraapi.controllers;

import br.com.srbit.locadoraapi.dto.ClienteRecordDTO;
import br.com.srbit.locadoraapi.models.ClienteModel;
import br.com.srbit.locadoraapi.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteModel> save(@RequestBody @Valid ClienteRecordDTO clienteRecordDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteRecordDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClienteRecordDTO clienteRecordDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(clienteRecordDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) throws Exception{
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
