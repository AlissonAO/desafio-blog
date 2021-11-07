package br.com.frwk.blog.controller.dto;


import br.com.frwk.blog.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Alisson.Oliveira
 */
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return (List)usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}

