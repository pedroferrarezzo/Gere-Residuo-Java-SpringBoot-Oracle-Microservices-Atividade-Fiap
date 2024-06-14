package br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Morador;

public record RetornoListaMoradoresBairro(
        Long id,

        Long bairro,

        String nomeMorador,

        String emailMorador
) {
    public RetornoListaMoradoresBairro(Morador morador) {
        this(
                morador.getId(),
                morador.getBairro().getId(),
                morador.getNomeMorador(),
                morador.getEmailMorador()
        );
    }
}
