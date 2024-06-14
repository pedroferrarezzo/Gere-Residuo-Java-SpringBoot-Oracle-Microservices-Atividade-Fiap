package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.TipoResiduo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NotificacaoExibicaoDto(
        Long id,

        Long agenda,

        String nomeMorador,
        LocalDate diaColeta,

        TipoResiduo tipoResiduo,
        String emailMorador,

        Integer prcColetaBairro

) {
    public NotificacaoExibicaoDto(Notificacao notificacao) {
        this(
                notificacao.getId(),
                notificacao.getAgenda().getId(),
                notificacao.getNomeMorador(),
                notificacao.getDiaColeta(),
                notificacao.getTipoResiduo(),
                notificacao.getEmailMorador(),
                notificacao.getPrcColetaBairro()
        );
    }
}
