package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.dto.AgenciaRecordDTO;
import br.com.srbit.locadoraapi.exceptions.DataBaseException;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.models.AgenciaModel;
import br.com.srbit.locadoraapi.models.EnderecoModel;
import br.com.srbit.locadoraapi.repositories.AgenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@Service
public class AgenciaService {
    private final AgenciaRepository repository;
    private final EnderecoService enderecoService;

    public List<AgenciaModel> findAll(){
        return repository.findAll();
    }

    public AgenciaModel findById(UUID id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Agencia com ID: "+id+" não encontrado!"));
    }

    public AgenciaModel save(AgenciaRecordDTO agenciaRecordDTO) throws Exception {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setCidade(agenciaRecordDTO.cidade());
        enderecoModel.setBairro(agenciaRecordDTO.bairro());
        enderecoModel.setNumero(agenciaRecordDTO.numero());
        enderecoModel.setRua(agenciaRecordDTO.rua());
        enderecoModel.setEstado(agenciaRecordDTO.estado());

        enderecoModel = enderecoService.save(enderecoModel);

        AgenciaModel agenciaModel = new AgenciaModel();
        agenciaModel.setNome(agenciaRecordDTO.nome());
        agenciaModel.setLocalizacao(enderecoModel);

        try {
            return repository.save(agenciaModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public AgenciaModel update(AgenciaRecordDTO agenciaRecordDTO, UUID id) throws Exception {
        EnderecoModel enderecoModel = enderecoService.findById(agenciaRecordDTO.idEndereco());
        enderecoModel.setCidade(agenciaRecordDTO.cidade());
        enderecoModel.setBairro(agenciaRecordDTO.bairro());
        enderecoModel.setNumero(agenciaRecordDTO.numero());
        enderecoModel.setRua(agenciaRecordDTO.rua());
        enderecoModel.setEstado(agenciaRecordDTO.estado());

        enderecoModel = enderecoService.update(enderecoModel);

        AgenciaModel agenciaModel = new AgenciaModel();
        agenciaModel.setIdAgencia(id);
        agenciaModel.setNome(agenciaRecordDTO.nome());
        agenciaModel.setLocalizacao(enderecoModel);

        try {
            return repository.save(agenciaModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    public void delete(UUID id) throws Exception {
        var agenciaModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Agencia não encontrada com o  ID:"+ id));

        try {
            //enderecoService.delete(agenciaModel.getLocalizacao().getEnderecoId());
            repository.delete(agenciaModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }
}
