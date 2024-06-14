package br.com.fiap.gere.residuo.motorista.caminhao.ms.service;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaAtualizarDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaCadastroDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaDispExibicaoDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.exception.MotoristaNaoEncontradoException;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Motorista;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.repository.MotoristaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository motoristaRepository;

    public Motorista gravar(MotoristaCadastroDto motoristaCadastroDto) {

        Motorista motorista = new Motorista();
        BeanUtils.copyProperties(motoristaCadastroDto, motorista);
        motorista.setDisponivel(StatusDisponivel.DISPONIVEL);
        return motoristaRepository.save(motorista);
    }

    public Motorista atualizar(MotoristaAtualizarDto motoristaAtualizarDto) {

        Motorista motorista = new Motorista();
        BeanUtils.copyProperties(motoristaAtualizarDto, motorista);
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(motorista.getId());

        if (motoristaOptional.isPresent()) {
            motorista.setDisponivel(motoristaOptional.get().getDisponivel());
            return motoristaRepository.save(motorista);
        }

        else {

            throw new MotoristaNaoEncontradoException("Motorista não encontrado!");
        }

    }

    public void colocarEmOcupado(Long id) {

        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);

        if (motoristaOptional.isPresent()) {
            Motorista motorista = motoristaOptional.get();
            motorista.setDisponivel(StatusDisponivel.OCUPADO);
            motoristaRepository.save(motorista);
        } else {
            throw new MotoristaNaoEncontradoException("Motorista não encontrado!");
        }

    }

    public Motorista colocarEmDisponibilidade(Long id) {

        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);

        if (motoristaOptional.isPresent()) {
            Motorista motorista = motoristaOptional.get();
            motorista.setDisponivel(StatusDisponivel.DISPONIVEL);
            return motoristaRepository.save(motorista);
        } else {
            throw new MotoristaNaoEncontradoException("Motorista não encontrado!");
        }

    }

    public Page<Motorista> listarTodosMotoristas(Pageable paginacao) {
        return motoristaRepository
                .findAll(paginacao);
    }

    public Motorista buscarPorId(Long id) {
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);

        if (motoristaOptional.isPresent()) {
            return motoristaOptional.get();
        }

        else {
            throw new MotoristaNaoEncontradoException("Motorista não encontrado!");
        }

    }

    public MotoristaDispExibicaoDto retornarDisponibilidade(Long id) {
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);

        if (motoristaOptional.isPresent()) {
            return new MotoristaDispExibicaoDto(motoristaOptional.get());
        }

        else {
            throw new CaminhaoNaoEncontradoException("Motorista não encontrado!");
        }

    }

    public void excluirPorId(Long id) {
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);

        if (motoristaOptional.isPresent()) {
            motoristaRepository.delete(motoristaOptional.get());
        }

        else {
            throw new MotoristaNaoEncontradoException("Motorista não encontrado!");
        }
    }
}
