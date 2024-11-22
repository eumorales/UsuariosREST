package com.gilbertomorales.projeto.controller;

import com.gilbertomorales.projeto.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.add(usuario);
        return usuario;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst();
    }

    @PutMapping("/{id}")
    public Optional<Usuario> editarUsuario(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarios.stream().filter(u -> u.getId() == id).findFirst();
        usuarioExistente.ifPresent(u -> {
            u.setNome(usuarioAtualizado.getNome());
            u.setSobrenome(usuarioAtualizado.getSobrenome());
            u.setEmail(usuarioAtualizado.getEmail());
            u.setIdade(usuarioAtualizado.getIdade());
            u.setGenero(usuarioAtualizado.getGenero());
        });
        return usuarioExistente;
    }

    @DeleteMapping("/{id}")
    public boolean excluirUsuario(@PathVariable int id) {
        return usuarios.removeIf(u -> u.getId() == id);
    }
}

