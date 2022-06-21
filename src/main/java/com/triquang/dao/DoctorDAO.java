package com.triquang.dao;

import java.util.List;

import com.triquang.entity.Bacsi;
import com.triquang.entity.Benhnhan;

public class DoctorDAO extends JpaDAO<Bacsi> implements GenericDAO<Bacsi> {
	
	public DoctorDAO() {
	}
	
	@Override
	public Bacsi create(Bacsi bacsi) {
		return super.create(bacsi);
	}

	@Override
	public Bacsi update(Bacsi bacsi) {
		return super.update(bacsi);
	}

	@Override
	public Bacsi get(Object doctorId) {
		return super.find(Bacsi.class, doctorId);
	}

	@Override
	public void delete(Object doctorId) {
		super.delete(Bacsi.class, doctorId);
		
	}

	@Override
	public List<Bacsi> listAll() {
		return super.findWithNamedQuery("Bacsi.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Bacsi.countAll");
	}

	public Bacsi findByName(String doctorName) {
		List<Bacsi> result = super.findWithNamedQuery("Bacsi.findByName", "name", doctorName);
		
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	public long countByDepartment(int doctorId) {
		return super.countWithNamedQuery("Bacsi.countByDepartment", "depId", doctorId);
	}
	
}
