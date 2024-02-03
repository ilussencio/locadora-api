package br.com.srbit.locadoraapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TABLE_RESERVA")
public class ReservaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReserva;

    @ManyToOne
    private CarroModel carroModel;

    @ManyToOne
    private ClienteModel clienteModel;

    private Instant dataLocacao;
    private int qtdDias;
    private BigDecimal valorTotal;

    @CreationTimestamp(source = SourceType.DB)
    private Instant create_at;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant update_at;
}
