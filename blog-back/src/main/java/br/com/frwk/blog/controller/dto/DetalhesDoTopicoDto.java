package br.com.frwk.blog.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.frwk.blog.modelo.StatusTopico;
import br.com.frwk.blog.modelo.Topico;
/**
 * @author Alisson.Oliveira
 */
public class DetalhesDoTopicoDto {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	private List<FotoDto> fotos;

	public DetalhesDoTopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.fotos.addAll(topico.getFotos().stream().map(FotoDto::new).collect(Collectors.toList()));
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return this.id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return this.dataCriacao;
	}

	public String getNomeAutor() {
		return this.nomeAutor;
	}

	public StatusTopico getStatus() {
		return this.status;
	}

	public List<RespostaDto> getRespostas() {
		return this.respostas;
	}
}
