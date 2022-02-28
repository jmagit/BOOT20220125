package com.example.application.dtos;

import com.example.domains.entities.City;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class CiudadDetailsDTO {
	@JsonProperty("id")
	private int cityId;
	@JsonProperty("ciudad")
	private String city;
	@JsonProperty("pais")
	private String country;

	public static CiudadDetailsDTO from(City source) {
		return new CiudadDetailsDTO(
				source.getCityId(), 
				source.getCity(),
				source.getCountry().getCountry());
	}
}
