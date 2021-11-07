package br.com.frwk.blog.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.frwk.blog.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * @author Alisson.Oliveira
 */
@Service
public class TokenService {
	@Value("${blog.jwt.expiration}")
	private String expiration;
	@Value("${blog.jwt.secret}")
	private String secret;

	public TokenService() {
	}

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario)authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(this.expiration));
		return Jwts.builder()
				.setIssuer("API do Blog da Framework")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje).setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public boolean tokenEhValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}