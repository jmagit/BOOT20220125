package com.example.basicos;

import java.util.ArrayList;
import java.util.List;

public class AlumnoRepositoryMock implements AlumnoRepository {

	public AlumnoRepositoryMock() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> lista = new ArrayList<>();
		lista.add(new Alumno(1, "uno", "", "1995-08-12"));
		lista.add(new Alumno(2, "dos", "apell", "2000-02-01"));
		lista.add(new Alumno(4, "tres", "", "2000-02-11"));
		return lista;
	}

	@Override
	public Alumno getOne(Integer id) {
		if(id == 666)
			throw new IllegalArgumentException("Fallo forzado");
		return new Alumno(id, "uno", "", "1995-08-12");
	}

	@Override
	public Alumno add(Alumno item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno modify(Alumno item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Alumno item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
