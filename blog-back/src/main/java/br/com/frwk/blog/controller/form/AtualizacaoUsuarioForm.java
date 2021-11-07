package br.com.frwk.blog.controller.form;

import br.com.frwk.blog.modelo.Usuario;
import br.com.frwk.blog.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * @author Alisson.Oliveira
 */
public class AtualizacaoUsuarioForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String nome;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String senha;

    public AtualizacaoUsuarioForm() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = (new BCryptPasswordEncoder()).encode(senha);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
        Usuario usuario = (Usuario)usuarioRepository.getOne(id);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }
}

