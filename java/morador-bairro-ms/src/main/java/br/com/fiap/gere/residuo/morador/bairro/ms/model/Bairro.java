package br.com.fiap.gere.residuo.morador.bairro.ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "T_BAIRRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bairro {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_BAIRRO_SEQUENCE"
    )
    @SequenceGenerator(
            name = "T_BAIRRO_SEQUENCE",
            sequenceName = "T_BAIRRO_SEQUENCE",
            allocationSize = 1
    )
    Long id;

    @OneToMany(mappedBy = "bairro")
    List<Morador> moradores;

    @Column(name = "nome_bairro")
    String nomeBairro;

    @Column(name = "qtd_lixeiras")
    Integer qtdLixeiras;

    @Column(name = "peso_m_lixeiras_kg")
    Integer pesoMLixeirasKg;

    @Column(name = "prc_coleta")
    Integer prcColeta;

    @Enumerated(EnumType.STRING)
    StatusDisponivel disponivel;

    public Bairro (Long id) {
        this.id = id;
    }
}
