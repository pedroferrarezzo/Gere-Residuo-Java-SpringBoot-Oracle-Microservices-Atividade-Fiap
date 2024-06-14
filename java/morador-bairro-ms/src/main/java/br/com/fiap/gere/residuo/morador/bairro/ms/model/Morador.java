package br.com.fiap.gere.residuo.morador.bairro.ms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_MORADOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Morador {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_MORADOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_MORADOR_SEQUENCE",
            sequenceName = "T_MORADOR_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    Bairro bairro;

    @Column(name = "nome_morador")
    String nomeMorador;

    @Column(name = "email_morador")
    String emailMorador;
}
