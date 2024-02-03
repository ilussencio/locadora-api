package br.com.srbit.locadoraapi.repositories;

import br.com.srbit.locadoraapi.models.ClienteModel;
import br.com.srbit.locadoraapi.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
    @Query(value = "select * from TABLE_RESERVA where cliente_model_id_cliente = ?1", nativeQuery = true)
    List<ReservaModel> findByClienteId(UUID idCliente);

    @Query(value = "select * from TABLE_RESERVA where carro_model_id_carro = ?1", nativeQuery = true)
    List<ReservaModel> findByCarroId(UUID idCarro);
}
