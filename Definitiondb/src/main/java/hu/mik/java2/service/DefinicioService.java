package hu.mik.java2.service;

import java.util.List;

import hu.mik.java2.definition.bean.Definicio;



public interface DefinicioService {
	public List<Definicio> listDefinicios();
	
	public Definicio getDefinicioById(Integer id);
	
	public Definicio saveDefinicio(Definicio book);
	
	public Definicio updateDefinicio(Definicio book);
	
	public void deleteDefinicio(Definicio book);
	
	public List<Definicio> listDefiniciosByAuthor(String author);
}
