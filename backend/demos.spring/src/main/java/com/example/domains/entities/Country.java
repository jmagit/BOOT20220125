package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import com.example.domains.core.entities.EntityBase;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country extends EntityBase<Country> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id")
	private int countryId;

	@NotBlank
	@Length(max = 50)
	private String country;

	@Column(name="last_update")
	@Generated(value = GenerationTime.ALWAYS)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country")
	private List<City> cities;

	public Country() {
	}

	public Country(int countryId) {
		super();
		this.countryId = countryId;
	}

	public Country(int countryId, @NotBlank @Length(max = 50) String country) {
		super();
		this.countryId = countryId;
		this.country = country;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Country))
			return false;
		Country other = (Country) obj;
		return countryId == other.countryId;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + "]";
	}

}