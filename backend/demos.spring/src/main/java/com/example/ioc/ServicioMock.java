package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("test")
public class ServicioMock implements Servicio {

	public ServicioMock() {
	}

	@Override
	public void run() {
		System.out.println("Soy de mentira");
	}

	@Override
	public void setName(String value) {
		
	}

}
