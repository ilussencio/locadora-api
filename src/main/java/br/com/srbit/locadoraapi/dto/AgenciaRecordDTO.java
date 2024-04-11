package br.com.srbit.locadoraapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AgenciaRecordDTO(
        @NotBlank String nome,
        UUID idEndereco,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotNull Integer numero,
        @NotBlank String rua,
        @NotBlank String estado
) {
}
