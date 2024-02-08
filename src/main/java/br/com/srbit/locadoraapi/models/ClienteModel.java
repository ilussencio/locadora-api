package br.com.srbit.locadoraapi.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TABLE_CLIENTE")
public class ClienteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;

    private String nome;
    private String telefone;
    private String email;

    @Column(unique = true)
    private String cpf;

    @CreationTimestamp(source = SourceType.DB)
    private Instant create_at;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant update_at;
}
