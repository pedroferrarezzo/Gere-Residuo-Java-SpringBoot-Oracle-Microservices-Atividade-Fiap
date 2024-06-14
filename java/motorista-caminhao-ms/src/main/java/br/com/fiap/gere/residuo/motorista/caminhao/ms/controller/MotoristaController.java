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
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Page<Motorista> listarTodosMotoristas(Pageable paginacao) {
        return motoristaService.listarTodosMotoristas(paginacao);
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Motorista buscarMotoristaPorId(@RequestParam(name = "id") Long id) {
        return motoristaService.buscarPorId(id);
    }

    @GetMapping(value = "/retornarDisponibilidade", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public MotoristaDispExibicaoDto retornarDisponibilidade(@RequestParam(name = "id") Long id) {
        return motoristaService.retornarDisponibilidade(id);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirMotoristaPorId(@RequestParam(name = "id") Long id) {
        motoristaService.excluirPorId(id);
    }
}
