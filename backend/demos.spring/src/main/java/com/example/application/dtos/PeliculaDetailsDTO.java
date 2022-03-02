package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.List;
import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PeliculaDetailsDTO {
	@JsonProperty("id")
	private int filmId;
	private String title;
	private String description;
	private Short releaseYear;
	private String language;
	private String languageVO;
	private byte rentalDuration;
	private BigDecimal rentalRate;
	private int length;
	private BigDecimal replacementCost;
	private String rating;
	private List<String> actores;
	private List<String> categorias;

	public static PeliculaDetailsDTO from(Film source) {
		return new PeliculaDetailsDTO(
				source.getFilmId(), 
				source.getTitle(), 
				source.getDescription(),
				source.getReleaseYear(), 
				source.getLanguage().getName(),
				source.getLanguageVO() == null ? null : source.getLanguageVO().getName(), 
						source.getRentalDuration(),
				source.getRentalRate(), 
				source.getLength(), 
				source.getReplacementCost(), 
				source.getRating(),
				source.getFilmActors().stream()
						.map(item -> item.getActor().getFirstName() + " " + item.getActor().getLastName()).sorted().toList(),
				source.getFilmCategories().stream().map(item -> item.getCategory().getName()).sorted().toList());
	}
}
