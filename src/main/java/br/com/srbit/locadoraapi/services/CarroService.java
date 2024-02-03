package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.dto.CarroRecordDTO;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.models.CarroModel;
import br.com.srbit.locadoraapi.repositories.CarroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarroService {
    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<CarroModel> findAll(){
        return repository.findAll();
    }

    public CarroModel findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Carro não entrado com o id: "+ id));
    }

    public CarroModel save(CarroRecordDTO carroRecordDTO){
        CarroModel carroModel = new CarroModel();
        BeanUtils.copyProperties(carroRecordDTO, carroModel);
        return repository.save(carroModel);
    }

    public CarroModel update(CarroRecordDTO carroRecordDTO, UUID id){
        var carroModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Carro não entrado com o id: "+ id));
        BeanUtils.copyProperties(carroRecordDTO, carroModel);
        return repository.save(carroModel);
    }

    public void delete(UUID id){
        var carroModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Carro não entrado com o id: "+ id));
        repository.delete(carroModel);
    }
}
