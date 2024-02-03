package br.com.srbit.locadoraapi.controllers;

import br.com.srbit.locadoraapi.dto.ReservaRecordDTO;
import br.com.srbit.locadoraapi.models.ReservaModel;
import br.com.srbit.locadoraapi.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReservaModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> findById(@PathVariable(name = "id") UUID id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<ReservaModel>> findByClientId(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCliente(id));
    }
    @GetMapping("/carro/{id}")
    public ResponseEntity<List<ReservaModel>> findByCarroId(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCarro(id));
    }

    @PostMapping
    public ResponseEntity<ReservaModel> save(@RequestBody @Valid ReservaRecordDTO reservaRecordDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservaRecordDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> update(@RequestBody ReservaRecordDTO reservaRecordDTO,
                                               @PathVariable(name = "id") UUID id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(reservaRecordDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Reserva deletada com sucesso!");
    }
}
