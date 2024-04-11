package br.com.srbit.locadoraapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TABLE_AGENCIA")
public class AgenciaModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAgencia;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    private EnderecoModel localizacao;
}
