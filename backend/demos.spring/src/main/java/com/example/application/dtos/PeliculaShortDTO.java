package com.example.application.dtos;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PeliculaShortDTO {
	@JsonProperty("id")
	private int filmId;
	@JsonProperty("titulo")
	private String title;
	
	public static PeliculaShortDTO from(Film source) {
		return new PeliculaShortDTO(
				source.getFilmId(),
				source.getTitle()
				);
	}
}
