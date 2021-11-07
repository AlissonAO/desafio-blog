package br.com.frwk.blog.repository;

import br.com.frwk.blog.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);

	List<Usuario> findByNome(String Usuario);
}
