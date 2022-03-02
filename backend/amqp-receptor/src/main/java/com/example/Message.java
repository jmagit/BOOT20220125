package com.example;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
	private MessageDTO msg;
	private Date recibido = new Date();
	
	public Message(MessageDTO msg) {
		this.msg = msg;
	}
}
