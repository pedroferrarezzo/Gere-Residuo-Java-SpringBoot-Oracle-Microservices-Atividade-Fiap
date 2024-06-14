package br.com.fiap.gere.residuo.motorista.caminhao.ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_CAMINHAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Caminhao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_CAMINHAO_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_CAMINHAO_SEQUENCE",
            sequenceName = "T_CAMINHAO_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    String placa;

    @Column(name = "ano_modelo")
    LocalDate anoModelo;

    String marca;

    String modelo;

    @Enumerated(EnumType.STRING)
    StatusDisponivel disponivel;
}
