package br.com.fiap.gere.residuo.agenda.notificacao.ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_NOTIFICACAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Notificacao {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_NOTIFICACAO_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_NOTIFICACAO_SEQUENCE",
            sequenceName = "T_NOTIFICACAO_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    Agenda agenda;


    @Column(name = "nome_morador")
    String nomeMorador;

    @Column(name = "dia_coleta")
    LocalDate diaColeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo")
    TipoResiduo tipoResiduo;

    @Column(name = "email_morador")
    String emailMorador;


    @Column(name = "prc_coleta_bairro")
    Integer prcColetaBairro;

}
