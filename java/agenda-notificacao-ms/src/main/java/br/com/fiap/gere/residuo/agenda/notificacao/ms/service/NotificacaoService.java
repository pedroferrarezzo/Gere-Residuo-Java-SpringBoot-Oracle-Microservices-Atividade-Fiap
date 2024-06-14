package br.com.fiap.gere.residuo.agenda.notificacao.ms.service;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao.NotificacaoExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.exception.AgendaFinalizadaException;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.exception.AgendaNaoEncontradaException;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.exception.NotificacaoNaoEncontradaException;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.StatusColeta;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.repository.AgendaRepository;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private EmailService emailService;

    public Page<NotificacaoExibicaoDto> listarTodasNotificacoes(Pageable paginacao) {
        return notificacaoRepository.findAll(paginacao).map(NotificacaoExibicaoDto::new);
    }

    public Page<NotificacaoExibicaoDto> dispararNotificacoesAgenda(Long id, Pageable paginacao) {
        Optional<Agenda> agendaOptional = agendaRepository.findById(id);

        if (agendaOptional.isPresent()) {
            if(agendaOptional.get().getStatusColeta().getStatus().equalsIgnoreCase(StatusColeta.EM_ANDAMENTO.getStatus())) {
                Page<NotificacaoExibicaoDto> notificacaoList= notificacaoRepository.findByAgenda(agendaOptional.get(), paginacao).map(NotificacaoExibicaoDto::new);

                if(!notificacaoList.getContent().isEmpty()) {
                    for(NotificacaoExibicaoDto notificao: notificacaoList.getContent()) {
                        emailService.sendEmail(
                                notificao.emailMorador(),
                                "ALERTA - Coleta de lixo agendada!",
                                String.format("Olá %s! Por meio deste informamos que uma nova coleta de lixo foi agendada para o seu bairro!\n" +
                                        "A data de coleta será: %s\n" +
                                        "O tipo de resíduo coletado será: %s\n" +
                                        "Lembrando que o percentual de lixo coletado atual do seu bairro é: %d%%",
                                        notificao.nomeMorador(),
                                        notificao.diaColeta(),
                                        notificao.tipoResiduo(),
                                        notificao.prcColetaBairro())
                        );

                        notificacaoRepository.deleteById(notificao.id());
                    }

                    return notificacaoList;
                }

                else {
                    throw new NotificacaoNaoEncontradaException("Não há notificações abertas para esta agenda!");
                }
            }

            else {
                throw new AgendaFinalizadaException("Agenda já finalizada!");
            }

        }

        else {
            throw new AgendaNaoEncontradaException("Agenda não encontrada!");
        }

    }


}
