package br.com.fiap.gere.residuo.agenda.notificacao.ms.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoBairroDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoCaminhaoDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoListaMoradoresBairro;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoMotoristaDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.model.http.Bairro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("morador.bairro.ms")
public interface MoradorBairroMsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/bairro/retornarDisponibilidade", params = "id")
    RetornoBairroDispExibicaoDto retornaDisponibilidadeBairro(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/bairro/buscarMoradores", params = "idBairro")
    Page<RetornoListaMoradoresBairro> retornaListaMoradoresBairro(@RequestParam(name = "idBairro") Long idBairro);

    @RequestMapping(method = RequestMethod.GET, value = "/bairro", params = "id")
    Bairro retornaBairroPorId(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/bairro/disponibilizar", params = "id")
    void disponibilizarBairro(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/bairro/ocupar", params = "id")
    void ocuparBairro(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/bairro/atualizarPrcColeta", params = {"id", "prcColeta"})
    void atualizarPrcColeta(@RequestParam(name = "id") Long id, @RequestParam(name = "prcColeta") Integer prcColeta);
}
