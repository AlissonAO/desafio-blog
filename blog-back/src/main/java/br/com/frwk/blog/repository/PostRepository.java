package br.com.frwk.blog.repository;

import br.com.frwk.blog.modelo.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByNome(String nome);

}
