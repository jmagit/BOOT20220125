package com.example.jdbc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Actor {
	private int actorId;
	private String firstName;
	private String lastName;
	private Date lastUpdate;

}
