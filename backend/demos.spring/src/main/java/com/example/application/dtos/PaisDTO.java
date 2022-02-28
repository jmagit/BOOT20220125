package com.example.application.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.domains.entities.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PaisDTO {
	@JsonProperty("id")
	private int countryId;
	@JsonProperty("pais")
	private String country;
	
	public static Country from(PaisDTO source) {
		return new Country(source.getCountryId(), source.getCountry());
	}
	
	public static PaisDTO from(Country source) {
		return new PaisDTO(source.getCountryId(), source.getCountry());
	}

}
