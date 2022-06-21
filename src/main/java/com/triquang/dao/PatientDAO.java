package com.triquang.dao;

import java.util.List;

import com.triquang.entity.Benhnhan;
import com.triquang.entity.Khoa;

public class PatientDAO extends JpaDAO<Benhnhan> implements GenericDAO<Benhnhan> {

public PatientDAO() {
		
	}
	
	@Override
	public Benhnhan create(Benhnhan benhnhan) {
		return super.create(benhnhan);
	}

	@Override
	public Benhnhan update(Benhnhan benhnhan) {
		return super.update(benhnhan);
	}

	@Override
	public Benhnhan get(Object id) {
		return super.find(Benhnhan.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Benhnhan.class, id);
		
	}

	@Override
	public List<Benhnhan> listAll() {
		return super.findWithNamedQuery("Benhnhan.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Benhnhan.countAll");
	}

	public Benhnhan findByName(String patientName) {
		List<Benhnhan> result = super.findWithNamedQuery("Benhnhan.findByName", "name", patientName);
		
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

}
