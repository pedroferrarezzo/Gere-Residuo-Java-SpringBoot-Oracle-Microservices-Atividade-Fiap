package br.com.fiap.gere.residuo.morador.bairro.ms.service;

import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorAtualizarDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorCadastroDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.exception.MoradorNaoEncontradoException;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Morador;
import br.com.fiap.gere.residuo.morador.bairro.ms.repository.BairroRepository;
import br.com.fiap.gere.residuo.morador.bairro.ms.repository.MoradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoradorService {
    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private BairroRepository bairroRepository;

    public MoradorExibicaoDto gravar(MoradorCadastroDto moradorCadastroDto) {

        Morador morador = new Morador();
        BeanUtils.copyProperties(moradorCadastroDto, morador);

        Optional<Bairro> bairroOptional = bairroRepository.findById(morador.getBairro().getId());

        if (bairroOptional.isPresent()) {
            return new MoradorExibicaoDto(moradorRepository.save(morador));
        }

        else {
            throw new DataIntegrityViolationException("O bairro passado não existe!");
        }


    }

    public MoradorExibicaoDto atualizar(MoradorAtualizarDto moradorAtualizarDto) {

        Morador morador = new Morador();
        BeanUtils.copyProperties(moradorAtualizarDto, morador);

        Optional<Bairro> bairroOptional = bairroRepository.findById(morador.getBairro().getId());
        Optional<Morador> moradorOptional = moradorRepository.findById(morador.getId());

        if (bairroOptional.isPresent() && moradorOptional.isPresent()) {
            return new MoradorExibicaoDto(moradorRepository.save(morador));
        }

        else if (moradorOptional.isPresent()) {
            throw new DataIntegrityViolationException("O bairro passado não existe!");
        }

        else {
            throw new MoradorNaoEncontradoException("Morador não encontrado!");
        }

    }

    public Page<MoradorExibicaoDto> listarTodosMoradores(Pageable paginacao) {
        return moradorRepository
                .findAll(paginacao)
                .map(MoradorExibicaoDto::new);
    }

    public MoradorExibicaoDto buscarPorId(Long id) {
        Optional<Morador> moradorOptional = moradorRepository.findById(id);

        if (moradorOptional.isPresent()) {
            return new MoradorExibicaoDto(moradorOptional.get());
        } else {
            throw new MoradorNaoEncontradoException("Morador não encontrado!");
        }

    }

    public void excluirPorId(Long id) {
        Optional<Morador> moradorOptional = moradorRepository.findById(id);

        if (moradorOptional.isPresent()) {
            moradorRepository.delete(moradorOptional.get());
        } else {
            throw new MoradorNaoEncontradoException("Morador não encontrado!");
        }
    }

}
