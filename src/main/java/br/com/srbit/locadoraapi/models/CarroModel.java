package br.com.srbit.locadoraapi.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TABLE_CARROS")
public class CarroModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCarro;

    private String marca;
    private String modelo;
    @Column(unique = true)
    private String placa;
    private BigDecimal preco;

    @CreationTimestamp(source = SourceType.DB)
    private Instant create_at;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant update_at;
}
