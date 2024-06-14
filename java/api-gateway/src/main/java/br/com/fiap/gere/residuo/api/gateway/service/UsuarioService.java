package br.com.fiap.gere.residuo.api.gateway.service;

import br.com.fiap.gere.residuo.api.gateway.Model.Usuario;
import br.com.fiap.gere.residuo.api.gateway.dto.UsuarioAtualizarDto;
import br.com.fiap.gere.residuo.api.gateway.dto.UsuarioCadastroDto;
import br.com.fiap.gere.residuo.api.gateway.dto.UsuarioExibicaoDto;
import br.com.fiap.gere.residuo.api.gateway.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gere.residuo.api.gateway.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioCadastroDto)
    {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario();

        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDto(usuarioSalvo);
    }

    public UsuarioExibicaoDto atualizarUsuario(UsuarioAtualizarDto usuarioAtualizarDto)
    {
        Optional <Usuario> optionalUsuario = usuarioRepository.findById(usuarioAtualizarDto.id());

        if (optionalUsuario.isPresent()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioAtualizarDto.senha());
            Usuario usuario = new Usuario();

            BeanUtils.copyProperties(usuarioAtualizarDto, usuario);

            usuario.setSenha(senhaCriptografada);

            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            return new UsuarioExibicaoDto(usuarioSalvo);
        }

        else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public void excluirUsuario(Long id)
    {
        Optional <Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
        }

        else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public Page<UsuarioExibicaoDto> listarTodosUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioExibicaoDto> usuarioDtos = usuarios.getContent().stream().map(UsuarioExibicaoDto::new).collect(Collectors.toList());
        return new PageImpl<>(usuarioDtos, pageable, usuarios.getTotalElements());


    }

    public Mono<Usuario> buscarPorEmail(String email) {

        Optional <Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            return Mono.just(optionalUsuario.get());
        }

        else {
            return Mono.just(new Usuario());
        }
    }
}
