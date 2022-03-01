package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City extends EntityBase<City> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id")
	private int cityId;

	@NotBlank
	@Length(max=50)
	private String city;

	@Column(name="last_update")
	@Generated(value = GenerationTime.ALWAYS)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	@JsonIgnore
	private List<Address> addresses;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

	public City() {
	}
	
	public City(int cityId) {
		super();
		this.cityId = cityId;
	}

	public City(int cityId, @NotBlank @Length(max = 50) String city, Country country) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.country = country;
	}

	public City(int cityId, @NotBlank @Length(max = 50) String city, List<Address> addresses) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.addresses = addresses;
	}



	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCity(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCity(null);

		return address;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof City))
			return false;
		City other = (City) obj;
		return cityId == other.cityId;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + "]";
	}

}