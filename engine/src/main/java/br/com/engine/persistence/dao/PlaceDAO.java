package br.com.engine.persistence.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.engine.persistence.beans.Place;
import br.com.engine.persistence.core.GenericDAO;

public class PlaceDAO extends GenericDAO<Place> {
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Place findById(Long id) {
		return (Place) this.getCriteria().add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Place findByCoordinates(Integer x, Integer y) {
		return (Place) this.getCriteria().add(Restrictions.eq("id", (long) ((x * 5000) + y + 1)))
				.uniqueResult();
	}
}