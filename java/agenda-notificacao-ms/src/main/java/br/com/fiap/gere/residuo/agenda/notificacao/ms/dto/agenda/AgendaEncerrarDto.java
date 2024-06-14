package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.StatusColeta;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.TipoResiduo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendaEncerrarDto(

        @NotNull(message = "O ID da agenda é obrigatório!")
        Long id,

        @NotNull(message = "A quantidade de lixo coletado em kg é obrigatóro!")
        Integer pesoColetaKg

) {
}
