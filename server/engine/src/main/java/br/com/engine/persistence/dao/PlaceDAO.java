package br.com.engine.persistence.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.commons.utils.Utils;
import br.com.engine.persistence.beans.Place;
import br.com.engine.persistence.beans.Unit;
import br.com.engine.persistence.core.GenericDAO;

public class PlaceDAO extends GenericDAO<Place> {

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Place findById(Long id) {
		return (Place) this.getCriteria().add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Place findByCoordinates(Integer x, Integer y) {
		return (Place) this.getCriteria()
				.add(Restrictions.eq("id", Utils.convertCoordinateToId(x, y)))
				.uniqueResult();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void clearUnitOnPlace(Integer x, Integer y) {
		Place place = (Place) this.getCriteria()
				.add(Restrictions.eq("id", Utils.convertCoordinateToId(x, y)))
				.uniqueResult();
		place.setUnit(null);
		super.update(place);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Place place) {
		super.update(place);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void putUnit(Place place, Unit unit) {
		place.setUnit(unit);
		unit.setPlace(place);
		this.update(place);
	}
}