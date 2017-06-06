package hu.mik.java2.definition.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import hu.mik.java2.definition.bean.Definicio;
import hu.mik.java2.util.HibernateUtil;

//@Repository
public class DefinicioDaoHibernateImpl implements DefinitionDao {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Definicio findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Definicio> findAll() {
		Session session = this.hibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Definicio.class);
		@SuppressWarnings("unchecked")
		List<Definicio> list = criteria.list();
		
		transaction.commit();
		
		return list;
	}

	@Override
	public Definicio save(Definicio definition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Definicio definition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Definicio> findByDefinitionLike(String definition) {
		// TODO Auto-generated method stub
		return null;
	}

}
