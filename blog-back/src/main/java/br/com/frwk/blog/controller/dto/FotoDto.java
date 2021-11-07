package br.com.frwk.blog.controller.dto;

import br.com.frwk.blog.modelo.Foto;
/**
 * @author Alisson.Oliveira
 */
public class FotoDto {
    private Long id;
    private String url;
    private String alt;

    public FotoDto(Foto foto) {
        this.id = foto.getId();
        this.url = foto.getUrl();
        this.alt = foto.getAlt();
    }

    public Long getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getAlt() {
        return this.alt;
    }
}