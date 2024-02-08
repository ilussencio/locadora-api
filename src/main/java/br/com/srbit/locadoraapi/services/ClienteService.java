package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.dto.ClienteRecordDTO;
import br.com.srbit.locadoraapi.exceptions.DataBaseException;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.models.ClienteModel;
import br.com.srbit.locadoraapi.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public List<ClienteModel> findAll(){
        return repository.findAll();
    }

    public ClienteModel findById(UUID id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
    }

    public ClienteModel save(ClienteRecordDTO clienteRecordDTO) throws Exception{
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDTO, clienteModel);

        try {
            return repository.save(clienteModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public ClienteModel update(ClienteRecordDTO clienteRecordDTO, UUID id) throws Exception {
        var clienteModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
        BeanUtils.copyProperties(clienteRecordDTO, clienteModel);
        try {
            return repository.save(clienteModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public void delete(UUID id) throws Exception{
        var clienteModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não entrado com o id: "+ id));
        try {
            repository.delete(clienteModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }
}
