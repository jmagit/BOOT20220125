package com.example.application.dtos;

import com.example.domains.entities.City;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class CiudadShortDTO {
	@JsonProperty("id")
	private int cityId;
	@JsonProperty("ciudad")
	private String city;

	public static CiudadShortDTO from(City source) {
		return new CiudadShortDTO(source.getCityId(), source.getCity());
	}
}
