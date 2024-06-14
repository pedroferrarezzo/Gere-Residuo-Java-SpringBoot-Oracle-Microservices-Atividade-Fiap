package br.com.fiap.gere.residuo.agenda.notificacao.ms.repository;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
