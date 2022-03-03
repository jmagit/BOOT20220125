package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Película editable", description = "Version editable de las películas.")
public class PeliculaEditDTO {
	@JsonProperty("id")
	private int filmId;
	@NotBlank
	@Length(max = 128)
	@ApiModelProperty(value = "Longitud máxima de 128 caracteres.")
	private String title;
	@ApiModelProperty(notes = "Cadena sin limite de longitud.")
	private String description;
	@Min(1901)
	@Max(2155)
	@ApiModelProperty(value = "Valor entre 1901 y 2155.")
	private Short releaseYear;
	@NotNull
	@Positive
	@ApiModelProperty(value = "Identificador del idioma de la película.")
	private int language;
	@Positive
	@ApiModelProperty(value = "Identificador del idioma de la versión original de la película.")
	private Integer languageVO;
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(value = "Entre 1 y 255.")
	private byte rentalDuration;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 2, fraction = 2)
	@ApiModelProperty(value = "Un máximo de 2 dígitos enteros y 2 decimales.")
	private BigDecimal rentalRate;
	@Positive
	@ApiModelProperty(value="Duración en minutos de la película, mayor que 0.", allowableValues = "range(1, infinity)")
	private int length;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 3, fraction = 2)
	@ApiModelProperty(value = "Un máximo de 3 dígitos enteros y 2 decimales.")
	private BigDecimal replacementCost;
	@ApiModelProperty(value = "Clasificación por edades.", allowableValues = "G,PG,PG-13,R,NC-17")
	private String rating;
	@ApiModelProperty(value = "Lista de identificadores de actores.")
	private List<Integer> actores;
	@ApiModelProperty(value = "Lista de identificadores de categoría.")
	private List<Integer> categorias;

	public static PeliculaEditDTO from(Film source) {
		return new PeliculaEditDTO(
				source.getFilmId(), 
				source.getTitle(),
				source.getDescription(),
				source.getReleaseYear(),
				source.getLanguage().getLanguageId(),
				source.getLanguageVO() == null ? null : source.getLanguageVO().getLanguageId(),
				source.getRentalDuration(),
				source.getRentalRate(),
				source.getLength(),
				source.getReplacementCost(),
				source.getRating() == null ? null : source.getRating().getValue(),
				source.getFilmActors().stream().map(item -> item.getActor().getActorId()).sorted().toList(),
				source.getFilmCategories().stream().map(item -> item.getCategory().getCategoryId()).sorted().toList()
				);
	}
	public static Film from(PeliculaEditDTO source) {
		return new Film (
				source.getFilmId(), 
				source.getTitle(),
				source.getDescription(),
				source.getReleaseYear(),
				new Language(source.getLanguage()),
				source.getLanguageVO() == null ? null : new Language(source.getLanguageVO()),
				source.getRentalDuration(),
				source.getRentalRate(),
				source.getLength(),
				source.getReplacementCost(),
				source.getRating() == null ? null : Film.Rating.getEnum(source.getRating())
				);
	}
	
	public Film update(Film target) {
//		target.setFilmId(filmId);
		target.setTitle(title);
		target.setDescription(description);
		target.setReleaseYear(releaseYear);
		if(target.getLanguage().getLanguageId() != language)
			target.setLanguage(new Language(language));
		target.setLanguageVO(languageVO == null ? null : new Language(languageVO));
		target.setRentalDuration(rentalDuration);
		target.setRentalRate(rentalRate);
		target.setLength(length);
		target.setReplacementCost(replacementCost);
		target.setRating(rating == null ? null : Film.Rating.getEnum(rating));
//ENTITY(1,3,5)
//DTO(1,7)
		// Borra los actores que sobran
		var delActores = target.getFilmActors().stream()
				.filter(item -> !actores.contains(item.getActor().getActorId()))
				.toList();
		delActores.forEach(item -> target.removeFilmActor(item));
		// Añade los actores que falta
		actores.stream()
			.filter(idActorDTO -> !target.getFilmActors().stream().anyMatch(filmActor -> filmActor.getActor().getActorId() == idActorDTO))
			.forEach(idActorDTO -> target.addFilmActor(new Actor(idActorDTO)));
//			.forEach(idActorDTO -> {
//				var filmActor = new FilmActor();
//				filmActor.setFilm(target);
//				filmActor.setActor(new Actor(idActorDTO));
//				var pk = new FilmActorPK();
//				pk.setActorId(idActorDTO);
//				pk.setFilmId(filmId);
//				filmActor.setId(pk);
//				target.getFilmActors().add(filmActor);
//			});
		// Borra las categorias que sobran
		var delCategorias = target.getFilmCategories().stream()
				.filter(item -> !categorias.contains(item.getCategory().getCategoryId()))
				.toList();
		delCategorias.forEach(item -> target.removeFilmCategory(item));
		// Añade las categorias que falta
		categorias.stream()
			.filter(idCategoriaDTO -> target.getFilmCategories().stream().noneMatch(filmCategory -> filmCategory.getCategory().getCategoryId() == idCategoriaDTO))
			.forEach(idCategoriaDTO -> target.addFilmCategory(new Category(idCategoriaDTO)));
		return target;
	}
}
