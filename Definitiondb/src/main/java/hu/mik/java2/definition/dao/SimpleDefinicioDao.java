package hu.mik.java2.definition.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hu.mik.java2.definition.bean.Definicio;

public interface SimpleDefinicioDao extends JpaRepository<Definicio, Integer> {
	public List<Definicio> findByDefinitionLike(String definition);
}
