package br.com.frwk.blog.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.frwk.blog.modelo.Usuario;
import br.com.frwk.blog.repository.UsuarioRepository;
/**
 * @author Alisson.Oliveira
 */
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = this.recuperarToken(request);
		boolean valido = this.tokenService.tokenEhValido(token);
		if (valido) {
			this.autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = this.tokenService.getIdUsuario(token);
		Usuario usuario = (Usuario)this.repository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, (Object)null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return token != null && !token.isEmpty() && token.startsWith("Bearer ") ? token.substring(7, token.length()) : null;
	}
}