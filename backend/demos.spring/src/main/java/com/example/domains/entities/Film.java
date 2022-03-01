package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
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

import com.example.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name = "film")
@NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
public class Film extends EntityBase<Film> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private int filmId;

	@NotBlank
	@Length(max = 128)
	private String title;

	@Lob
	private String description;

	@Column(name = "release_year")
	@Min(1901)
	@Max(2155)
	private Short releaseYear;

	// bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	// bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language languageVO;

	@Column(name = "rental_duration")
	@NotNull
	@PositiveOrZero
	private byte rentalDuration;

	@Column(name = "rental_rate")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 2, fraction = 2)
	private BigDecimal rentalRate;

	@Positive
	private int length;

	@Column(name = "replacement_cost")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 3, fraction = 2)
	private BigDecimal replacementCost;

	private String rating;

	@Column(name = "last_update")
	@Generated(value = GenerationTime.ALWAYS)
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FilmActor> filmActors;

	// bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FilmCategory> filmCategories;

	// bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy = "film")
	private List<Inventory> inventories;

	public Film() {
		super();
		filmActors = new ArrayList<>();
		filmCategories = new ArrayList<>();
		inventories = new ArrayList<>();
	}

	public Film(int filmId) {
		this();
		this.filmId = filmId;
	}

	public Film(int filmId, @NotBlank @Length(max = 128) String title, String description,
			@Min(1901) @Max(2155) short releaseYear, Language language, Language languageVO,
			@NotNull @Positive byte rentalDuration,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 2, fraction = 2) BigDecimal rentalRate,
			@Positive int length,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal replacementCost,
			String rating) {
		this();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.languageVO = languageVO;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Short getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguageVO() {
		return this.languageVO;
	}

	public void setLanguageVO(Language languageVO) {
		this.languageVO = languageVO;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setFilm(this);

		return filmActor;
	}

	public FilmActor addFilmActor(Actor actor) {
		var filmActor = new FilmActor(actor, this);
		getFilmActors().add(filmActor);
		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}

	public FilmActor removeFilmActor(Actor actor) {
//		var filmActor = getFilmActors().stream().filter(item -> item.getId().getActorId() == actor.getActorId())
//				.findFirst();
//		if (filmActor.isPresent()) {
//			getFilmActors().remove(filmActor.get());
//			filmActor.get().setFilm(null);
//		}
		var filmActor = new FilmActor(actor, this);
		getFilmActors().remove(filmActor);
		return filmActor;
	}

	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().add(filmCategory);
		filmCategory.setFilm(this);

		return filmCategory;
	}

	public FilmCategory addFilmCategory(Category category) {
		var filmCategory = new FilmCategory(category, this);
		getFilmCategories().add(filmCategory);
		return filmCategory;
	}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);

		return filmCategory;
	}

	public FilmCategory removeFilmCategory(Category category) {
		var filmCategory = new FilmCategory(category, this);
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);
		return filmCategory;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setFilm(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setFilm(null);

		return inventory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Film))
			return false;
		return filmId == ((Film) obj).filmId;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + "]";
	}

}