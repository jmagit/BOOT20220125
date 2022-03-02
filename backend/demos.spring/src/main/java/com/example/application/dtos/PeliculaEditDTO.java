package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.List;


import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PeliculaEditDTO {
	@JsonProperty("id")
	private int filmId;
	private String title;
	private String description;
	private Short releaseYear;
	private int language;
	private Integer languageVO;
	private byte rentalDuration;
	private BigDecimal rentalRate;
	private int length;
	private BigDecimal replacementCost;
	private String rating;
	private List<Integer> actores;
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
				source.getRating(),
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
				source.getRating()
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
		target.setRating(rating);
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
