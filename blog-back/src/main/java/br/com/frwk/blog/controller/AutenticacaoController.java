package br.com.frwk.blog.controller;

import br.com.frwk.blog.config.security.TokenService;
import br.com.frwk.blog.controller.dto.TokenDto;
import br.com.frwk.blog.controller.form.LoginForm;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alisson.Oliveira
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/autentica"})
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;


	public AutenticacaoController() {
	}

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = this.authManager.authenticate(dadosLogin);
			String token = this.tokenService.gerarToken(authentication);
			Long id =tokenService.getIdUsuario(token);
			String roles ="ADMIN"; //tem que implementar o perfil de usuário dinamico
			return ResponseEntity.ok(new TokenDto(token, "Bearer", roles));
		} catch (AuthenticationException var5) {
			return ResponseEntity.badRequest().build();
		}
	}
}
