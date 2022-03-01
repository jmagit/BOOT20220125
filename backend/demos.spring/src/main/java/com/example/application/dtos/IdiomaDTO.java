package com.example.application.dtos;

import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class IdiomaDTO {
	@JsonProperty("id")
	private int languageId;
	@JsonProperty("idioma")
	private String name;
	
	public static Language from(IdiomaDTO source) {
		return new Language(source.getLanguageId(), source.getName());
	}
	
	public static IdiomaDTO from(Language source) {
		return new IdiomaDTO(source.getLanguageId(), source.getName());
	}

}
