package br.com.fiap.gere.residuo.agenda.notificacao.ms.repository;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao.NotificacaoExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Page<Notificacao> findByAgenda(Agenda agenda, Pageable paginacao);
    List<Notificacao> findByAgenda(Agenda agenda);
}
