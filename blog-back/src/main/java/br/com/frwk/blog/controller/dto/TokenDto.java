package br.com.frwk.blog.controller.dto;

/**
 * @author Alisson.Oliveira
 */
public class TokenDto {
	private String token;
	private String tipo;
	private String roles;

	public TokenDto(String token, String tipo, String roles) {
		this.token = token;
		this.tipo = tipo;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getRoles(){ return roles; }
}
