package br.com.fiap.gere.residuo.morador.bairro.ms.controller;

import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorAtualizarDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorCadastroDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.dto.morador.MoradorExibicaoDto;
import br.com.fiap.gere.residuo.morador.bairro.ms.model.Morador;
import br.com.fiap.gere.residuo.morador.bairro.ms.service.MoradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/morador")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MoradorExibicaoDto gravar(@RequestBody @Valid MoradorCadastroDto MoradorCadastroDto) {
        return moradorService.gravar(MoradorCadastroDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public MoradorExibicaoDto atualizar(@RequestBody @Valid MoradorAtualizarDto MoradorAtualizarDto) {
        return moradorService.atualizar(MoradorAtualizarDto);
    }

    @GetMapping()
    public ResponseEntity<Page<MoradorExibicaoDto>> listarTodosMoradores(Pageable paginacao) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Page<MoradorExibicaoDto> moradores = moradorService.listarTodosMoradores(paginacao);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(moradores);
    }

    @GetMapping(params = "id")
    public ResponseEntity<MoradorExibicaoDto> buscarMoradorPorId(@RequestParam(name = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        MoradorExibicaoDto morador = moradorService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(morador);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirMoradorPorId(@RequestParam(name = "id") Long id) {
        moradorService.excluirPorId(id);
    }
}
