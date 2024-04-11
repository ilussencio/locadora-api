package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.exceptions.DataBaseException;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.models.EnderecoModel;
import br.com.srbit.locadoraapi.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@Service
public class EnderecoService {
    private final EnderecoRepository repository;

    public List<EnderecoModel> findAll(){
        return repository.findAll();
    }
    public EnderecoModel findById(UUID id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Endereco com o ID: "+id+" não localizado!"));
    }
    public EnderecoModel save(EnderecoModel enderecoModel) throws Exception{
        try {
            return repository.save(enderecoModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public EnderecoModel update(EnderecoModel enderecoModel) throws Exception{
        try {
            return repository.save(enderecoModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public void delete(UUID id) throws Exception {
        var enderecoModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Endereco com o ID: "+ id + " não localizado!"));
        try {
            repository.delete(enderecoModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }
}
