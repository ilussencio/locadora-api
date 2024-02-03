package br.com.srbit.locadoraapi.repositories;

import br.com.srbit.locadoraapi.models.CarroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<CarroModel, UUID> {
}
