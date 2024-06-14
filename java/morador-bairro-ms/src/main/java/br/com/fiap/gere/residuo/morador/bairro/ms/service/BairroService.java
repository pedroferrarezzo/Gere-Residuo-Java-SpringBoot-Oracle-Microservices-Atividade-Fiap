package br.com.fiap.gere.residuo.morador.bairro.ms.service;

import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroAtualizarDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroCadastroDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroDispExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.exception.BairroDmlException;
import br.com.fiap.gere.residuo.morador.bairro.ms.exception.BairroNaoEncontradoException;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Morador;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.StatusDisponivel;
import br.com.fiap.gere.residuo.morador.bairro.ms.repository.BairroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {
    @Autowired
    private BairroRepository bairroRepository;

    public BairroExibicaoDto gravar(BairroCadastroDto bairroCadastroDto) {

        Bairro bairro = new Bairro();
        BeanUtils.copyProperties(bairroCadastroDto, bairro);
        bairro.setDisponivel(StatusDisponivel.DISPONIVEL);

        if (bairro.getQtdLixeiras().equals(0) || bairro.getPesoMLixeirasKg().equals(0)) {
            throw new BairroDmlException("A quantidade de lixeiras ou o peso médio de cada lixeira é igual a 0!");
        }

        if(bairro.getPrcColeta().equals(0) || bairro.getPrcColeta() > 100) {
            throw new BairroDmlException("O percentual de coleta é igual 0 ou maior que 100!");
        }



        return new BairroExibicaoDto(bairroRepository.save(bairro));
    }

    public BairroExibicaoDto atualizar(BairroAtualizarDto bairroAtualizarDto) {

        Bairro bairro = new Bairro();
        BeanUtils.copyProperties(bairroAtualizarDto, bairro);
        Optional<Bairro> bairroOptional = bairroRepository.findById(bairro.getId());

        if (bairro.getQtdLixeiras().equals(0) || bairro.getPesoMLixeirasKg().equals(0)) {
            throw new BairroDmlException("A quantidade de lixeiras ou o peso médio de cada lixeira é igual a 0!");
        }

        if(bairro.getPrcColeta().equals(0) || bairro.getPrcColeta() > 100) {
            throw new BairroDmlException("O percentual de coleta é igual 0 ou maior que 100!");
        }

        if (bairroOptional.isPresent()) {
            bairro.setDisponivel(bairroOptional.get().getDisponivel());

            return new BairroExibicaoDto(bairroRepository.save(bairro));
        } else {

            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }

    }

    public void colocarEmOcupado(Long id) {

        Optional<Bairro> BairroOptional = bairroRepository.findById(id);

        if (BairroOptional.isPresent()) {
            Bairro Bairro = BairroOptional.get();
            Bairro.setDisponivel(StatusDisponivel.OCUPADO);
            bairroRepository.save(Bairro);
        } else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }

    }

    public void colocarEmDisponibilidade(Long id) {

        Optional<Bairro> BairroOptional = bairroRepository.findById(id);

        if (BairroOptional.isPresent()) {
            Bairro Bairro = BairroOptional.get();
            Bairro.setDisponivel(StatusDisponivel.DISPONIVEL);
            bairroRepository.save(Bairro);
        } else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }
    }

    public void atualizarPrcColeta(Long id, Integer prcColeta) {

        Optional<Bairro> BairroOptional = bairroRepository.findById(id);

        if (BairroOptional.isPresent()) {

            if(prcColeta.equals(0) || prcColeta > 100) {
                throw new BairroDmlException("O percentual de coleta é igual 0 ou maior que 100!");
            }

            Bairro Bairro = BairroOptional.get();
            Bairro.setPrcColeta(prcColeta);
            bairroRepository.save(Bairro);
        }

        else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }
    }

    public Page<BairroExibicaoDto> listarTodosBairross(Pageable paginacao) {

        return bairroRepository
                .findAll(paginacao)
                .map(BairroExibicaoDto::new);
    }

    public BairroExibicaoDto buscarPorId(Long id) {
        Optional<Bairro> bairroOptional = bairroRepository.findById(id);

        if (bairroOptional.isPresent()) {
            return new BairroExibicaoDto(bairroOptional.get());
        } else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }

    }

    public Page<MoradorExibicaoDto> buscarMoradores(Long id_bairro, Pageable paginacao) {
        Optional<Bairro> bairroOptional = bairroRepository.findById(id_bairro);

        if (bairroOptional.isPresent()) {
            List<Morador> moradores = bairroOptional.get().getMoradores();

            int start = (int) paginacao.getOffset();
            int end = Math.min((start + paginacao.getPageSize()), moradores.size());

            return new PageImpl<MoradorExibicaoDto>
                    (moradores
                            .stream()
                            .map(MoradorExibicaoDto::new)
                            .toList()
                            .subList(start, end),
                            paginacao,
                            moradores.size());
        } else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }

    }



    public BairroDispExibicaoDto retornarDisponibilidade(Long id) {
        Optional<Bairro> bairroOptional = bairroRepository.findById(id);


        if (bairroOptional.isPresent()) {
            return new BairroDispExibicaoDto(bairroOptional.get());
        }

        else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }

    }

    public void excluirPorId(Long id) {
        Optional<Bairro> bairroOptional = bairroRepository.findById(id);

        if (bairroOptional.isPresent()) {
            bairroRepository.delete(bairroOptional.get());
        } else {
            throw new BairroNaoEncontradoException("Bairro não encontrado!");
        }
    }
}
