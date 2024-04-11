package br.com.srbit.locadoraapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TABLE_ENDERECO")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID enderecoId;

    private String cidade;
    private String bairro;
    private Integer numero;
    private String rua;
    private String estado;
}
