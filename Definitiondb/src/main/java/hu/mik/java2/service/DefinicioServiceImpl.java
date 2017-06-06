package hu.mik.java2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.mik.java2.definition.bean.Definicio;
import hu.mik.java2.definition.dao.SimpleDefinicioDao;

@Service("definitionServiceImpl")
public class DefinicioServiceImpl implements DefinicioService {

	@Autowired
	private SimpleDefinicioDao definitionDao;
	
	@Override
	public List<Definicio> listDefinicios() {
		return this.definitionDao.findAll();
	}

	@Override
	public Definicio getDefinicioById(Integer id) {
		return this.definitionDao.findOne(id);
	}

	@Override
	public Definicio saveDefinicio(Definicio book) {
		return this.definitionDao.save(book);
	}

	@Override
	public Definicio updateDefinicio(Definicio book) {
		return this.definitionDao.save(book);
	}

	@Override
	public void deleteDefinicio(Definicio book) {
		this.definitionDao.delete(book);
	}

	@Override
	public List<Definicio> listDefiniciosByAuthor(String definition) {
		return this.definitionDao.findByDefinitionLike(definition);
	}

}
