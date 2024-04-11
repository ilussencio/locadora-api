package br.com.srbit.locadoraapi.repositories;

import br.com.srbit.locadoraapi.models.AgenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgenciaRepository extends JpaRepository<AgenciaModel, UUID> { }
