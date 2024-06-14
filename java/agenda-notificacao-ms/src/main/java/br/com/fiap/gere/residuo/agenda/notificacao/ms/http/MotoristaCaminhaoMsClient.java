package br.com.fiap.gere.residuo.agenda.notificacao.ms.http;

import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoCaminhaoDispExibicaoDto;
import br.com.fiap.gere.residuo.agenda.notificacao.ms.dto.http.RetornoMotoristaDispExibicaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("motorista.caminhao.ms")
public interface MotoristaCaminhaoMsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/motorista/retornarDisponibilidade", params = "id")
    RetornoMotoristaDispExibicaoDto retornaDisponibilidadeMotorista(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/motorista/disponibilizar", params = "id")
    void disponibilizarMotorista(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/motorista/ocupar", params = "id")
    void ocuparMotorista(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/caminhao/retornarDisponibilidade", params = "id")
    RetornoCaminhaoDispExibicaoDto retornaDisponibilidadeCaminhao(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/caminhao/disponibilizar", params = "id")
    void disponibilizarCaminhao(@RequestParam(name = "id") Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/caminhao/ocupar", params = "id")
    void ocuparCaminhao(@RequestParam(name = "id") Long id);
}
