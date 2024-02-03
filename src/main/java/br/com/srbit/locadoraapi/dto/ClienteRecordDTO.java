package br.com.srbit.locadoraapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRecordDTO(
        @NotBlank String nome,
        @NotBlank String telefone,
        @CPF @NotBlank String cpf
) { }
