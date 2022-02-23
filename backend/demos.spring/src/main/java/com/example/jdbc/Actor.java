package com.example.jdbc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
//@Entity
//@Table(name = "actors")
public class Actor {
	@Id
	@Column(name = "actor_id")
	private int actorId;
	private String firstName;
	private String lastName;
	private Date lastUpdate;

}
