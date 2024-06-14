package br.com.fiap.gere.residuo.morador.bairro.ms.controller;

import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroAtualizarDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroCadastroDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroDispExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.bairro.BairroExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Bairro;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Morador;
import br.com.fiap.gere.residuo.morador.bairro.ms.service.BairroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {
    @Autowired
    private BairroService bairroService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BairroExibicaoDto gravar(@RequestBody @Valid BairroCadastroDto bairroCadastroDto) {
        return bairroService.gravar(bairroCadastroDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public BairroExibicaoDto atualizar(@RequestBody @Valid BairroAtualizarDto bairroAtualizarDto) {
        return bairroService.atualizar(bairroAtualizarDto);
    }

    @PutMapping(value = "/ocupar",params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void colocarEmOcupado(@RequestParam(name = "id") Long id) {
        bairroService.colocarEmOcupado(id);
    }

    @PutMapping(value = "/disponibilizar", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void colocarEmDisponibilidade(@RequestParam(name = "id") Long id) {
        bairroService.colocarEmDisponibilidade(id);
    }

    @PutMapping(value = "/atualizarPrcColeta", params = {"id", "prcColeta"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPrcColeta(@RequestParam(name = "id") Long id, @RequestParam(name = "prcColeta") Integer prcColeta) {
        bairroService.atualizarPrcColeta(id, prcColeta);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<BairroExibicaoDto> listarTodosBairros(Pageable paginacao) {
        return bairroService.listarTodosBairross(paginacao);
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public BairroExibicaoDto buscarBairroPorId(@RequestParam(name = "id") Long id) {
        return bairroService.buscarPorId(id);
    }

    @GetMapping(value = "/buscarMoradores", params = "idBairro")
    @ResponseStatus(HttpStatus.OK)
    public Page<MoradorExibicaoDto> buscarMoradores(@RequestParam(name = "idBairro") Long id, Pageable paginacao) {
        return bairroService.buscarMoradores(id, paginacao);
    }

    @GetMapping(value = "/retornarDisponibilidade", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public BairroDispExibicaoDto retornarDisponibilidade(@RequestParam(name = "id") Long id) {
        return bairroService.retornarDisponibilidade(id);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirBairroPorId(@RequestParam(name = "id") Long id) {
        bairroService.excluirPorId(id);
    }
}
