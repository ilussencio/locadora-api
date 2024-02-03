package br.com.srbit.locadoraapi.controllers;

import br.com.srbit.locadoraapi.dto.CarroRecordDTO;
import br.com.srbit.locadoraapi.models.CarroModel;
import br.com.srbit.locadoraapi.repositories.CarroRepository;
import br.com.srbit.locadoraapi.services.CarroService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
public class CarroController {


    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarroModel> save(@RequestBody @Valid CarroRecordDTO carroRecordDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(carroRecordDTO));
    }

    @GetMapping
    public ResponseEntity<List<CarroModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarroRecordDTO carroRecordDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(carroRecordDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso!");
    }
}
