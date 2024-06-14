package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.TipoResiduo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NotificacaoCadastroDto(
        Long id,

        @NotNull(message = "O ID da agenda é obrigatório!")
        Agenda agenda,

        @NotBlank(message = "O nome do morador é obrigatório!")
        @Size(min = 1, max = 30, message = "O nome do morador deve estar entre 1 e 30 caracteres!")
        String nomeMorador,

        @NotNull(message = "A data de coleta é obrigatória!")
        LocalDate diaColeta,


        @NotNull(message = "O tipo residuo é obrigatório!")
        TipoResiduo tipoResiduo,

        @NotBlank(message = "O email do morador é obrigatório!")
        @Email(message = "O email do morador deve estar no formato: meuemail@domain.com!")
        String emailMorador,

        @NotNull(message = "O percentual de coleta atual do bairro é obrigatório!")
        Integer prcColetaBairro

) {

}
