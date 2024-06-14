package br.com.fiap.gere.residuo.motorista.caminhao.ms.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "T_MOTORISTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Motorista {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_MOTORISTA_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_MOTORISTA_SEQUENCE",
            sequenceName = "T_MOTORISTA_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    @Column(name = "nome_motorista")
    String nomeMotorista;

    @Column(name = "nr_cpf")
    String nrCpf;

    @Column(name = "nr_celular")
    String nrCelular;

    @Column(name = "nr_celular_ddd")
    String nrCelularDdd;

    @Column(name = "nr_celular_ddi")
    String nrCelularDdi;

    @Enumerated(EnumType.STRING)
    StatusDisponivel disponivel;

}
