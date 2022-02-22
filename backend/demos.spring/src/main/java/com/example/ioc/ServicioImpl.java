package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
@Qualifier("despliegue")
@Scope("prototype")
public class ServicioImpl implements Servicio {
//	private Dependencia dependencia;
	private String name;
	public ServicioImpl(Dependencia dep) {
//		dependencia = dep;
		name = dep.getName();
	}

	@Override
	public void run() {
//		System.out.println("Soy el servicio de " + dependencia.getName());
		System.out.println("Soy el servicio de " + name);
	}

	@Override
	public void setName(String value) {
		name = value;		
	}


}
