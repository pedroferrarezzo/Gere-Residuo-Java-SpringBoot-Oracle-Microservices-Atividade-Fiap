package br.com.fiap.gere.residuo.motorista.caminhao.ms.controller;

import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoAtualizarDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoCadastroDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.dto.caminhao.CaminhaoDispExibicaoDto;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.Caminhao;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.model.StatusDisponivel;
import br.com.fiap.gere.residuo.motorista.caminhao.ms.service.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caminhao")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Caminhao gravar(@RequestBody @Valid CaminhaoCadastroDto caminhaoCadastroDto) {
        return caminhaoService.gravar(caminhaoCadastroDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Caminhao atualizar(@RequestBody @Valid CaminhaoAtualizarDto caminhaoAtualizarDto) {
        return caminhaoService.atualizar(caminhaoAtualizarDto);
    }

    @PutMapping(value = "/ocupar",params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void colocarEmOcupado(@RequestParam(name = "id") Long id) {
        caminhaoService.colocarEmOcupado(id);
    }

    @PutMapping(value = "/disponibilizar", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void colocarEmDisponibilidade(@RequestParam(name = "id") Long id) {
        caminhaoService.colocarEmDisponibilidade(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<Caminhao> listarTodosMotoristas(Pageable paginacao) {
        return caminhaoService.listarTodosMotoristas(paginacao);
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Caminhao buscarMotoristaPorId(@RequestParam(name = "id") Long id) {
        return caminhaoService.buscarPorId(id);

    }

    @GetMapping(value = "/retornarDisponibilidade", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public CaminhaoDispExibicaoDto retornarDisponibilidade(@RequestParam(name = "id") Long id) {
        return caminhaoService.retornarDisponibilidade(id);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCaminhaoPorId(@RequestParam(name = "id") Long id) {
        caminhaoService.excluirPorId(id);
    }

}
