package hu.mik.java2.service;

import java.util.List;

import hu.mik.java2.definition.bean.Definicio;



public interface DefinicioService {
	public List<Definicio> listDefinicios();
	
	public Definicio getDefinicioById(Integer id);
	
	public Definicio saveDefinicio(Definicio definition);
	
	public Definicio updateDefinicio(Definicio definition);
	
	public void deleteDefinicio(Definicio definition);
	
	public List<Definicio> listDefiniciosByDefinition(String definition);
}
