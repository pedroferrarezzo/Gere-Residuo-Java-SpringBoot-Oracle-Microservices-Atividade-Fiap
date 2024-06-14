package br.com.fiap.gere.residuo.api.gateway.controller;

import br.com.fiap.gere.residuo.api.gateway.config.security.AuthenticationManager;
import br.com.fiap.gere.residuo.api.gateway.dto.*;
import br.com.fiap.gere.residuo.api.gateway.service.JWTService;
import br.com.fiap.gere.residuo.api.gateway.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<TokenExibicaoDto>> login(@RequestBody @Valid LoginDto loginDto) {

//        throw new RuntimeException(new BCryptPasswordEncoder().encode(loginDto.senha()));

        return usuarioService.buscarPorEmail(loginDto.email())

                .filter(userDetails -> new BCryptPasswordEncoder().matches(loginDto.senha(),userDetails.getPassword()))

                .map(userDetails -> ResponseEntity.ok(new TokenExibicaoDto(jwtService.generateToken(userDetails))))

                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        UsuarioExibicaoDto usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDto);
        return usuarioSalvo;
    }

    @PutMapping("/atualizarUsuario")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizarUsuario(@RequestBody @Valid UsuarioAtualizarDto usuarioAtualizarDto) {
        UsuarioExibicaoDto usuarioAtualizado = usuarioService.atualizarUsuario(usuarioAtualizarDto);
        return usuarioAtualizado;
    }

    @GetMapping("/listarUsuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> listarUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return usuarioService
                .listarTodosUsuarios(pageable);
    }

    @DeleteMapping(value = "/excluirUsuario", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@RequestParam("id") Long id) {
        usuarioService.excluirUsuario(id);
    }

}
