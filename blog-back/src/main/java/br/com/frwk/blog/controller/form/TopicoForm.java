package br.com.frwk.blog.controller.form;

import br.com.frwk.blog.modelo.Foto;
import br.com.frwk.blog.modelo.Post;
import br.com.frwk.blog.modelo.Topico;
import br.com.frwk.blog.repository.PostRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alisson.Oliveira
 */
public class TopicoForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;
	@NotNull
	@NotEmpty
	private String nomePost;

	private List<Foto> fotos;

	public TopicoForm() {
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setNomePost(String nomePost) {
		this.nomePost = nomePost;
	}

	public void setFotos() { this.fotos = fotos; }

	public Topico converter(PostRepository cursoRepository) {
		Post post = cursoRepository.findByNome(this.nomePost);
		return new Topico(this.titulo, this.mensagem, post);
	}
}
