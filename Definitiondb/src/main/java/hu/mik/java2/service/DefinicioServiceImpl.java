package hu.mik.java2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.mik.java2.definition.bean.Definicio;
import hu.mik.java2.definition.dao.DefinitionDao;
import hu.mik.java2.definition.dao.DefinitionDaoImpl;
import hu.mik.java2.definition.dao.SimpleDefinicioDao;

@Service("definitionServiceImpl")
public class DefinicioServiceImpl implements DefinicioService {

	@Autowired
	private DefinitionDao definitionDao;
	
	@Override
	public List<Definicio> listDefinicios() {
		return this.definitionDao.findAll();
	}

	@Override
	public Definicio getDefinicioById(Integer id) {
		return this.definitionDao.findOne(id);
	}

	@Override
	public Definicio saveDefinicio(Definicio definition) {
		return this.definitionDao.save(definition);
	}

	@Override
	public Definicio updateDefinicio(Definicio definition) {
		return this.definitionDao.save(definition);
	}

	@Override
	public void deleteDefinicio(Definicio definition) {
		this.definitionDao.delete(definition);
	}

	@Override
	public List<Definicio> listDefiniciosByDefinition(String definition) {
		return this.definitionDao.findByDefinitionLike(definition);
	}

}
