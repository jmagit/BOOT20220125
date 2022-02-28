package com.example.application.dtos;

import com.example.domains.entities.City;
import com.example.domains.entities.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class CiudadEditDTO {
	@JsonProperty("id")
	private int cityId;
	@JsonProperty("ciudad")
	private String city;
	@JsonProperty("pais")
	private int country;

	public static CiudadEditDTO from(City source) {
		return new CiudadEditDTO(
				source.getCityId(), 
				source.getCity(),
				source.getCountry().getCountryId());
	}
	public static City from(CiudadEditDTO source) {
		return new City(
				source.getCityId(), 
				source.getCity(),
				new Country(source.getCountry())
				);
	}
}
