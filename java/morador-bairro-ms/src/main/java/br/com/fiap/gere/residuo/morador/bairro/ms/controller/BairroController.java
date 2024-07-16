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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<BairroExibicaoDto>> listarTodosBairros(Pageable paginacao) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Page<BairroExibicaoDto> bairros = bairroService.listarTodosBairross(paginacao);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(bairros);
    }

    @GetMapping(params = "id")
    public ResponseEntity<BairroExibicaoDto> buscarBairroPorId(@RequestParam(name = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        BairroExibicaoDto bairro = bairroService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(bairro);
    }

    @GetMapping(value = "/buscarMoradores", params = "idBairro")
    public ResponseEntity<Page<MoradorExibicaoDto>> buscarMoradores(@RequestParam(name = "idBairro") Long id, Pageable paginacao) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Page<MoradorExibicaoDto> moradores = bairroService.buscarMoradores(id, paginacao);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(moradores);
    }

    @GetMapping(value = "/retornarDisponibilidade", params = "id")
    public ResponseEntity<BairroDispExibicaoDto> retornarDisponibilidade(@RequestParam(name = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        BairroDispExibicaoDto disponibilidade = bairroService.retornarDisponibilidade(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(disponibilidade);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirBairroPorId(@RequestParam(name = "id") Long id) {
        bairroService.excluirPorId(id);
    }
}
