package br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bairro {
    Long id;

    StatusDisponivel disponivel;

    Integer pesoMLixeirasKg;

    Integer qtdLixeiras;

    Integer prcColeta;
}
