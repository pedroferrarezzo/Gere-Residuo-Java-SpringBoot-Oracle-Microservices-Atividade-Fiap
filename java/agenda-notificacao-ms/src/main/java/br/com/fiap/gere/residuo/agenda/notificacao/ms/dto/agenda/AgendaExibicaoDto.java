package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.StatusColeta;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.TipoResiduo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendaExibicaoDto(
        Long id,

        Long idCaminhao,

        Long idMotorista,

        Long idBairro,

        LocalDate diaColeta,

        TipoResiduo tipoResiduo,
        Integer pesoColetaKg,

        StatusColeta statusColeta
) {
    public AgendaExibicaoDto(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getIdCaminhao(),
                agenda.getIdMotorista(),
                agenda.getIdBairro(),
                agenda.getDiaColeta(),
                agenda.getTipoResiduo(),
                agenda.getPesoColetaKg(),
                agenda.getStatusColeta()
        );
    }
}
