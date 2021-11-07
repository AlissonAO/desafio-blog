package br.com.frwk.blog.repository;

import br.com.frwk.blog.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	List<Topico> findByPostNome(String nomePost);
}

