package br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Morador {
    Long id;

    Bairro bairro;

    String nomeMorador;

    String emailMorador;

}
