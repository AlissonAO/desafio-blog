package br.com.frwk.blog.controller;

import br.com.frwk.blog.controller.dto.UsuarioDto;
import br.com.frwk.blog.controller.form.AtualizacaoUsuarioForm;
import br.com.frwk.blog.controller.form.UsuarioForm;
import br.com.frwk.blog.modelo.Usuario;
import br.com.frwk.blog.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Alisson.Oliveira
 */
@CrossOrigin({"*"})
@RestController
@RequestMapping({"/usuarios"})
public class UsuariosController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuariosController() {
    }

    @GetMapping
    @Cacheable({"listaDeUsuarios"})
    public List<UsuarioDto> lista() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = {"listaDeUsuarios"}, allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioForm form) {
        Usuario usuario = form.cadUsuario(form);
        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{nome}"})
    public List<UsuarioDto> detalhar(@PathVariable String nome) {
        List<Usuario> usuario = this.usuarioRepository.findByNome(nome);
        return usuario != null ? UsuarioDto.converter(usuario) : (List)ResponseEntity.notFound().build();
    }

    @PutMapping({"/{id}"})
    @Transactional
    @CacheEvict(value = {"listaDeUsuarios"}, allEntries = true)
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm form) {
        Optional<Usuario> optional = this.usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = form.atualizar(id, this.usuarioRepository);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping({"/{id}"})
    @Transactional
    @CacheEvict(value = {"listaDeUsuarios"}, allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Usuario> optional = this.usuarioRepository.findById(id);
        if (optional.isPresent()) {
            this.usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
