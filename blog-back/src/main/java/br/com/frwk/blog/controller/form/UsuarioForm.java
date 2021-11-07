package br.com.frwk.blog.controller.form;

import br.com.frwk.blog.modelo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Alisson.Oliveira
 */
public class UsuarioForm {
    private String email;
    private String senha;
    private String nome;

    public UsuarioForm() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario cadUsuario(UsuarioForm form) {
        Usuario usuario = new Usuario();
        usuario.setEmail(form.getEmail());
        usuario.setSenha((new BCryptPasswordEncoder()).encode(form.getSenha()));
        usuario.setNome(form.getNome());
        return usuario;
    }
}
