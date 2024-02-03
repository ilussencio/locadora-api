package br.com.srbit.locadoraapi.services;

import br.com.srbit.locadoraapi.dto.ReservaRecordDTO;
import br.com.srbit.locadoraapi.exceptions.DataBaseException;
import br.com.srbit.locadoraapi.exceptions.NotFoundException;
import br.com.srbit.locadoraapi.exceptions.ReservConflictException;
import br.com.srbit.locadoraapi.models.CarroModel;
import br.com.srbit.locadoraapi.models.ClienteModel;
import br.com.srbit.locadoraapi.models.ReservaModel;
import br.com.srbit.locadoraapi.repositories.ReservaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {
    private final ReservaRepository repository;
    private final ClienteService clienteService;
    private final CarroService carroService;

    public ReservaService(ReservaRepository repository, ClienteService clienteService, CarroService carroService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.carroService = carroService;
    }

    public ReservaModel save(ReservaRecordDTO reservaRecordDTO) throws Exception {
        ReservaModel reservaModel = new ReservaModel();
        return getReservaModel(reservaRecordDTO, reservaModel);
    }

    public ReservaModel update(ReservaRecordDTO reservaRecordDTO, UUID id) throws Exception {
        ReservaModel reservaModel = this.findById(id);

        return getReservaModel(reservaRecordDTO, reservaModel);
    }

    public ReservaModel findById(UUID id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Reserva não encontrada! com id: "+ id));
    }

    public List<ReservaModel> findAll(){
        return repository.findAll();
    }

    public List<ReservaModel> findByCliente (UUID id){
        return repository.findByClienteId(id);
    }

    public List<ReservaModel> findByCarro(UUID id) {
        return repository.findByCarroId(id);
    }

    public void delete(UUID id){
        ReservaModel reservaModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Reserva não encontrada! com id: "+ id));

        try {
            repository.delete(reservaModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

    private boolean verificarConflito(ReservaModel reservaExistente, ReservaRecordDTO novaReserva) {
        Instant dataLocacaoExistente = reservaExistente.getDataLocacao();
        Instant dataDevolucaoExistente = dataLocacaoExistente.plus(Period.ofDays(reservaExistente.getQtdDias()));

        Instant novaDataLocacao = novaReserva.dataLocacao();
        Instant novaDataDevolucao = novaDataLocacao.plus(Period.ofDays(novaReserva.qtdDias()));

        return !(novaDataDevolucao.isBefore(dataLocacaoExistente) || novaDataLocacao.isAfter(dataDevolucaoExistente));
    }

    private ReservaModel getReservaModel(ReservaRecordDTO reservaRecordDTO, ReservaModel reservaModel) throws Exception {

        List<ReservaModel> reservasExistentes = this.findByCarro(reservaRecordDTO.carroId());
        for (ReservaModel reservaExistente : reservasExistentes) {
            if (verificarConflito(reservaExistente, reservaRecordDTO)) {
                throw new ReservConflictException("O carro não esta disponivel da data solicitada");
            }
        }

        reservaModel.setClienteModel(clienteService.findById(reservaRecordDTO.clienteId()));

        CarroModel carroModel = carroService.findById(reservaRecordDTO.carroId());
        reservaModel.setCarroModel(carroModel);

        reservaModel.setDataLocacao(reservaRecordDTO.dataLocacao());
        reservaModel.setQtdDias(reservaRecordDTO.qtdDias());

        reservaModel.setValorTotal(carroModel.getPreco().multiply(new BigDecimal(reservaRecordDTO.qtdDias())));

        try {
            return repository.save(reservaModel);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Violação de integridade do banco de dados");
        }
    }

}
