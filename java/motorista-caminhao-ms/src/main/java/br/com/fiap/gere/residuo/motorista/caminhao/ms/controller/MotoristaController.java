package br.com.fiap.gere.residuo.motorista.caminhao.ms.controller;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaAtualizarDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaCadastroDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.motorista.MotoristaDispExibicaoDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Motorista;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.service.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Motorista gravar(@RequestBody @Valid MotoristaCadastroDto motoristaCadastroDto) {
        return motoristaService.gravar(motoristaCadastroDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Motorista atualizar(@RequestBody @Valid MotoristaAtualizarDto motoristaAtualizarDto) {
        return motoristaService.atualizar(motoristaAtualizarDto);
    }

    @PutMapping(value = "/ocupar",params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void colocarEmOcupado(@RequestParam(name = "id") Long id) {
        motoristaService.colocarEmOcupado(id);
    }

    @PutMapping(value = "/disponibilizar", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Motorista colocarEmDisponibilidade(@RequestParam(name = "id") Long id) {
        return motoristaService.colocarEmDisponibilidade(id);
    }

    @GetMapping()
    public ResponseEntity<Page<Motorista>> listarTodosMotoristas(Pageable paginacao) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Page<Motorista> motoristas = motoristaService.listarTodosMotoristas(paginacao);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(motoristas);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Motorista> buscarMotoristaPorId(@RequestParam(name = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        Motorista motorista = motoristaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(motorista);
    }

    @GetMapping(value = "/retornarDisponibilidade", params = "id")
    public ResponseEntity<MotoristaDispExibicaoDto> retornarDisponibilidade(@RequestParam(name = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public, max-age=600, must-revalidate");
        MotoristaDispExibicaoDto disponibilidade = motoristaService.retornarDisponibilidade(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(disponibilidade);
    }


    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirMotoristaPorId(@RequestParam(name = "id") Long id) {
        motoristaService.excluirPorId(id);
    }
}
