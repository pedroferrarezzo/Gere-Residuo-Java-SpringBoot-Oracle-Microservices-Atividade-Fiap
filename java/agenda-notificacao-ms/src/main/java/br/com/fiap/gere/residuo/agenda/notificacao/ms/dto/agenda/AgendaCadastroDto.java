package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.StatusColeta;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.TipoResiduo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendaCadastroDto(
        Long id,

        @NotNull(message = "O ID do caminhão é obrigatório!")
        Long idCaminhao,

        @NotNull(message = "O ID do motorista é obrigatório!")
        Long idMotorista,

        @NotNull(message = "O ID do bairro é obrigatório!")
        Long idBairro,

        @NotNull(message = "O dia de coleta é obrigatório!")
        LocalDate diaColeta,

        @NotNull(message = "O tipo de residuo que será coletado é obrigatório!")
        TipoResiduo tipoResiduo
) {
}
