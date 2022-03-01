package com.example.application.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import com.example.domains.entities.City;
import com.example.domains.entities.Film;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.Language;
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
				source.getFilmActors().stream().map(item -> item.getActor().getFirstName() + " " + item.getActor().getLastName())/*.sorted()*/.toList(),
				source.getFilmCategories().stream().map(item -> item.getCategory().getName())/*.sorted()*/.toList()
				);
	}
}
