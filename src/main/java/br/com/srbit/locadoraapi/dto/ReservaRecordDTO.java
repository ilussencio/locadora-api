package br.com.srbit.locadoraapi.dto;


import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record ReservaRecordDTO(
        @NotNull UUID carroId,
        @NotNull UUID clienteId,
        @NotNull Instant dataLocacao,
        @NotNull int qtdDias
) {
}
