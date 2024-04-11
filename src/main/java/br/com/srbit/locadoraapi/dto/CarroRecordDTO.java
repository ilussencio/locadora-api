package br.com.srbit.locadoraapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CarroRecordDTO(
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotBlank String placa,
        @NotNull UUID agencia,
        @NotNull BigDecimal preco,
        @NotBlank String fotoPrincipal,
        String fotoSecundaria
) {
}
