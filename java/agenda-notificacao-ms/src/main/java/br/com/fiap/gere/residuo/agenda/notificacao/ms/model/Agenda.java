package br.com.fiap.gere.residuo.agenda.notificacao.ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_AGENDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Agenda {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_AGENDA_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_AGENDA_SEQUENCE",
            sequenceName = "T_AGENDA_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    @OneToMany(mappedBy = "agenda")
    List<Notificacao> notificacoes;

    @Column(name = "id_caminhao")
    Long idCaminhao;

    @Column(name = "id_motorista")
    Long idMotorista;

    @Column(name = "id_bairro")
    Long idBairro;

    @Column(name = "dia_coleta")
    LocalDate diaColeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo")
    TipoResiduo tipoResiduo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_coleta")
    StatusColeta statusColeta;

    @Column(name = "peso_coleta_kg")
    Integer pesoColetaKg;

    public Agenda (Long id) {
        this.id = id;
    }
}
