package br.com.fiap.gere.residuo.agenda.notificacao.ms.controller;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaCadastroDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaEncerrarDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgendaExibicaoDto gravar(@RequestBody @Valid AgendaCadastroDto agendaCadastroDto) {
        return agendaService.agendarColeta(agendaCadastroDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public AgendaExibicaoDto encerrarAgenda(@RequestBody @Valid AgendaEncerrarDto agendaEncerrarDto) {
        return agendaService.EncerrarAgenda(agendaEncerrarDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<AgendaExibicaoDto> listarTodasAgendas(Pageable paginacao) {
        return agendaService.listarTodasAgendas(paginacao);
    }
}
