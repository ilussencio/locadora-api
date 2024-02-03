package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.dto.ClienteRecordDTO;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.models.ClienteModel;
import br.com.srbit.locadoraapi.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteModel> findAll(){
        return repository.findAll();
    }

    public ClienteModel findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
    }

    public ClienteModel save(ClienteRecordDTO clienteRecordDTO){
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDTO, clienteModel);
        return repository.save(clienteModel);
    }

    public ClienteModel update(ClienteRecordDTO clienteRecordDTO, UUID id){
        var clienteModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
        BeanUtils.copyProperties(clienteRecordDTO, clienteModel);
        return repository.save(clienteModel);
    }

    public void delete(UUID id){
        var clienteModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
        repository.delete(clienteModel);
    }
}
