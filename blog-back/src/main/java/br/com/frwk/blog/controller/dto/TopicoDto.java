package br.com.frwk.blog.controller.dto;

import br.com.frwk.blog.modelo.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Alisson.Oliveira
 */
public class TopicoDto {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private List<FotoDto> fotos;

	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.fotos = new ArrayList<>();
		this.fotos.addAll(topico.getFotos().stream().map(FotoDto::new).collect(Collectors.toList()));
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

	public static List<TopicoDto> converter(List<Topico> topicos) {
		return (List)topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
	}

	public List<FotoDto> getFotos() {
		return this.fotos;
	}
}