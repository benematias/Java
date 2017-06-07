package hu.mik.java2.definition.dao;

import java.util.List;


import hu.mik.java2.definition.bean.Definicio;

public interface DefinitionDao {
	public Definicio findOne(Integer id);
	
	public List<Definicio> findAll();
	
	public Definicio save(Definicio definicio);
	
	public void delete(Definicio definicio);
	
	public List<Definicio> findByDefinitionLike(String definition);
	
	public List<Definicio> listDefiniciosBySbject(String subject);
	
}
