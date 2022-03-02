package com.example;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MessageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String msg;
	private Date enviado = new Date();
	
	public MessageDTO(String msg) {
		this.msg = msg;
	}
}
