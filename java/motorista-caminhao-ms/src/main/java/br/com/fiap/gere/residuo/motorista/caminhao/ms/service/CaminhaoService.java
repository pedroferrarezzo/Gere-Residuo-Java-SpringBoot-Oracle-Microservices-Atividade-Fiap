package br.com.fiap.gere.residuo.motorista.caminhao.ms.service;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoAtualizarDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoCadastroDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoDispExibicaoDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.repository.CaminhaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    public Caminhao gravar(CaminhaoCadastroDto caminhaoCadastroDto) {

        Caminhao caminhao = new Caminhao();
        BeanUtils.copyProperties(caminhaoCadastroDto, caminhao);
        caminhao.setDisponivel(StatusDisponivel.DISPONIVEL);
        caminhao.setPlaca(caminhaoCadastroDto.placa().toUpperCase());
        return caminhaoRepository.save(caminhao);
    }

    public Caminhao atualizar(CaminhaoAtualizarDto caminhaoAtualizarDto) {

        Caminhao caminhao = new Caminhao();
        BeanUtils.copyProperties(caminhaoAtualizarDto, caminhao);
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(caminhao.getId());

        if (caminhaoOptional.isPresent()) {
            caminhao.setDisponivel(caminhaoOptional.get().getDisponivel());
            return caminhaoRepository.save(caminhao);
        } else {

            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }

    }

    public void colocarEmOcupado(Long id) {

        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

        if (caminhaoOptional.isPresent()) {
            Caminhao caminhao = caminhaoOptional.get();
            caminhao.setDisponivel(StatusDisponivel.OCUPADO);
            caminhaoRepository.save(caminhao);

        } else {
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }

    }

    public void colocarEmDisponibilidade(Long id) {

        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

        if (caminhaoOptional.isPresent()) {
            Caminhao caminhao = caminhaoOptional.get();
            caminhao.setDisponivel(StatusDisponivel.DISPONIVEL);
            caminhaoRepository.save(caminhao);
        } else {
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }
    }

    public Page<Caminhao> listarTodosMotoristas(Pageable paginacao) {
        return caminhaoRepository
                .findAll(paginacao);
    }

    public Caminhao buscarPorId(Long id) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

        if (caminhaoOptional.isPresent()) {
            return caminhaoOptional.get();
        } else {
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }

    }

    public CaminhaoDispExibicaoDto retornarDisponibilidade(Long id) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

        if (caminhaoOptional.isPresent()) {
            return new CaminhaoDispExibicaoDto(caminhaoOptional.get());
        } else {
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }

    }

    public void excluirPorId(Long id) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

        if (caminhaoOptional.isPresent()) {
            caminhaoRepository.delete(caminhaoOptional.get());
        } else {
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }
    }
}
