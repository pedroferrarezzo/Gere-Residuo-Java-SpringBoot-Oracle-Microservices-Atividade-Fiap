package br.com.fiap.gere.residuo.agenda.notificacao.ms.service;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaCadastroDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaEncerrarDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.agenda.AgendaExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoBairroDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoCaminhaoDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoListaMoradoresBairro;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoMotoristaDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.notificacao.NotificacaoExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.exception.*;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.http.MoradorBairroMsClient;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.http.MotoristaCaminhaoMsClient;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Agenda;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.Notificacao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.StatusColeta;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Bairro;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Caminhao;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Motorista;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.StatusDisponivel;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.repository.AgendaRepository;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.repository.NotificacaoRepository;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.utils.BairroUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private MoradorBairroMsClient moradorBairroMsClient;

    @Autowired
    private MotoristaCaminhaoMsClient motoristaCaminhaoMsClient;

    public AgendaExibicaoDto agendarColeta(AgendaCadastroDto agendaCadastroDto) {

        Agenda agenda = new Agenda();
        Bairro bairro;
        Agenda agendaSalva;

        BeanUtils.copyProperties(agendaCadastroDto, agenda);

        agenda.setStatusColeta(StatusColeta.EM_ANDAMENTO);
        agenda.setPesoColetaKg(0);

        RetornoMotoristaDispExibicaoDto statusDisponivelMotorista = motoristaCaminhaoMsClient.retornaDisponibilidadeMotorista(agenda.getIdMotorista());
        RetornoCaminhaoDispExibicaoDto statusDisponivelCaminhao = motoristaCaminhaoMsClient.retornaDisponibilidadeCaminhao(agenda.getIdCaminhao());
        RetornoBairroDispExibicaoDto statusDisponivelBairro = moradorBairroMsClient.retornaDisponibilidadeBairro(agenda.getIdBairro());




        if (
                statusDisponivelMotorista.disponivel().getStatus().equalsIgnoreCase(StatusDisponivel.DISPONIVEL.getStatus()) &&
                statusDisponivelCaminhao.disponivel().getStatus().equalsIgnoreCase(StatusDisponivel.DISPONIVEL.getStatus()) &&
                statusDisponivelBairro.disponivel().getStatus().equalsIgnoreCase(StatusDisponivel.DISPONIVEL.getStatus())
        ) {

            List<RetornoListaMoradoresBairro> listaMoradoresBairro = moradorBairroMsClient
                    .retornaListaMoradoresBairro(agenda.getIdBairro()).getContent();

            if (!listaMoradoresBairro.isEmpty()) {
                agendaSalva = agendaRepository.save(agenda);
                bairro = moradorBairroMsClient.retornaBairroPorId(agenda.getIdBairro());

                moradorBairroMsClient.ocuparBairro(agenda.getIdBairro());
                motoristaCaminhaoMsClient.ocuparMotorista(agenda.getIdMotorista());
                motoristaCaminhaoMsClient.ocuparCaminhao(agenda.getIdCaminhao());



                for (RetornoListaMoradoresBairro morador : listaMoradoresBairro) {
                    Notificacao notificacao = new Notificacao();

                    notificacao.setAgenda(agendaSalva);
                    notificacao.setNomeMorador(morador.nomeMorador());
                    notificacao.setDiaColeta(agendaSalva.getDiaColeta());
                    notificacao.setTipoResiduo(agendaSalva.getTipoResiduo());
                    notificacao.setEmailMorador(morador.emailMorador());
                    notificacao.setPrcColetaBairro(bairro.getPrcColeta());

                    notificacaoRepository.save(notificacao);
                }

                return new AgendaExibicaoDto(agendaSalva);
            }

            else {
                throw new MoradorNaoEncontradoException("Não existem moradores cadastrados para o bairro especificado!");
            }
        }

        else {
            throw new NaoDisponivelException("Certifique-se que o bairro, motorista e caminhão escolhido estejam disponíveis!");
        }
    }


    public AgendaExibicaoDto EncerrarAgenda(AgendaEncerrarDto agendaEncerrarDto) {
        Bairro bairro = new Bairro();

        Optional<Agenda> agendaOptional = agendaRepository.findById(agendaEncerrarDto.id());


        if(agendaOptional.isPresent()) {
            List<NotificacaoExibicaoDto> notificacaoList = notificacaoRepository.findByAgenda(agendaOptional.get()).stream().map(NotificacaoExibicaoDto::new).toList();

            if (agendaEncerrarDto.pesoColetaKg().equals(0)) {
                throw new AgendaPesoColetaInvalidoException("O peso de lixo coletado deve ser diferente de 0!");
            }

            else if (!notificacaoList.isEmpty()) {
                throw new NotificacaoPendenteException("Existem notificações para serem disparadas para esta agenda! Certifique-se de dispará-las antes de fechar esta agenda.");
            }

            else if(agendaOptional.get().getStatusColeta().getStatus().equalsIgnoreCase(StatusColeta.EM_ANDAMENTO.getStatus())) {
                agendaOptional.get().setStatusColeta(StatusColeta.FINALIZADA);
                agendaOptional.get().setPesoColetaKg(agendaEncerrarDto.pesoColetaKg());
                Agenda agendaAtualizada = agendaRepository.save(agendaOptional.get());

                motoristaCaminhaoMsClient.disponibilizarCaminhao(agendaOptional.get().getIdCaminhao());
                motoristaCaminhaoMsClient.disponibilizarMotorista(agendaOptional.get().getIdMotorista());
                moradorBairroMsClient.disponibilizarBairro(agendaOptional.get().getIdBairro());

                bairro = moradorBairroMsClient.retornaBairroPorId(agendaOptional.get().getIdBairro());

                Float prcColetaFloat = BairroUtils.calculaPercentualColeta(
                        agendaEncerrarDto.pesoColetaKg(),
                        bairro.getPesoMLixeirasKg(),
                        bairro.getQtdLixeiras()
                );

                Integer prcColeta = prcColetaFloat.intValue();

                moradorBairroMsClient.atualizarPrcColeta(agendaOptional.get().getIdBairro(), prcColeta);
                return new AgendaExibicaoDto(agendaAtualizada);
            }

            else {
                throw new AgendaFinalizadaException("A agenda passada já foi finalizada!");
            }

        }

        else {
            throw new AgendaNaoEncontradaException("Agenda não encontrada!");
        }
    }

    public Page<AgendaExibicaoDto> listarTodasAgendas(Pageable paginacao) {
        return agendaRepository.findAll(paginacao).map(AgendaExibicaoDto::new);
    }
}
