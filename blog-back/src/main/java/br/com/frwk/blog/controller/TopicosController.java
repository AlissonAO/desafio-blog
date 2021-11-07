package br.com.frwk.blog.controller;

import br.com.frwk.blog.controller.dto.DetalhesDoTopicoDto;
import br.com.frwk.blog.controller.dto.TopicoDto;
import br.com.frwk.blog.controller.form.AtualizacaoTopicoForm;
import br.com.frwk.blog.controller.form.TopicoForm;
import br.com.frwk.blog.modelo.Foto;
import br.com.frwk.blog.modelo.Topico;
import br.com.frwk.blog.repository.PostRepository;
import br.com.frwk.blog.repository.TopicoRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;

/**
 * @author Alisson.Oliveira
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/topicos"})
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UploadController uploadController;

	private List<Foto> fotos;

	public static final String uploadingDir = System.getProperty("user.dir") + "/upload/";

	public TopicosController() {
	}

	@GetMapping
	@Cacheable({"listaDeTopicos"})
	public List<TopicoDto> lista(String nomePost) {
		List topicos;
		if (nomePost == null) {
			topicos = this.topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			topicos = this.topicoRepository.findByPostNome(nomePost);
			return TopicoDto.converter(topicos);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = {"listaDeTopicos"},	allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(this.postRepository);
		this.topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(new Object[]{topico.getId()}).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping({"/{id}"})
	@Transactional
	@CacheEvict(value = {"listaDeTopicos"},	allEntries = true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
		Optional<Topico> optional = this.topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, this.topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping({"/{id}"})
	@Transactional
	@CacheEvict(value = {"listaDeTopicos"},	allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optional = this.topicoRepository.findById(id);
		if (optional.isPresent()) {
			this.topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
