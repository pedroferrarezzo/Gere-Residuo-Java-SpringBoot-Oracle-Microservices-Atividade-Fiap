package br.com.fiap.gere.residuo.agenda.notificacao.ms.controller;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaCadastroDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaEncerrarDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao.NotificacaoExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.service.AgendaService;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.service.EmailService;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private EmailService emailService;

    @GetMapping()
    public ResponseEntity<Page<NotificacaoExibicaoDto>> listarTodasNotificacoes(Pageable paginacao) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Page<NotificacaoExibicaoDto> notificacoes = notificacaoService.listarTodasNotificacoes(paginacao);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(notificacoes);
    }

    @PostMapping(value = "/dispararNotificacoes", params = "idAgenda")
    @ResponseStatus(HttpStatus.OK)
    public Page<NotificacaoExibicaoDto> dispararEmail(@RequestParam(name = "idAgenda") Long idAgenda, Pageable paginacao) {
        return notificacaoService.dispararNotificacoesAgenda(idAgenda, paginacao);
    }


}
