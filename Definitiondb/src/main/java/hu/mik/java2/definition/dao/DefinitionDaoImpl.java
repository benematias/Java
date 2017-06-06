package hu.mik.java2.definition.dao;

import java.io.Console;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.mik.java2.definition.bean.Definicio;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DefinitionDaoImpl implements DefinitionDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Definicio findOne(Integer id) {
		return this.entityManager.find(Definicio.class, id);
	}

	@Override
	public List<Definicio> findAll() {
		return this.entityManager
				.createQuery("SELECT b FROM Definicio b ORDER BY b.id DESC ", Definicio.class)
				.getResultList();
	}

	@Override
	public Definicio save(Definicio definition) {
		if(definition.getId() == null) {
			this.entityManager.persist(definition);
			
			return definition;
		} else {
			return this.entityManager.merge(definition);
		}
	}

	@Override
	public void delete(Definicio definition) {
		if(!this.entityManager.contains(definition)) {
			this.entityManager.merge(definition);
		}
		
		this.entityManager.remove(definition);
	}

	@Override
	public List<Definicio> findByDefinitionLike(String definitio) {
		return this.entityManager
			.createQuery("SELECT b FROM Definicio b WHERE b.definition LIKE :definicion", Definicio.class)
			.setParameter("definicion", "%"+definitio+"%")
			.getResultList();
	}
}
