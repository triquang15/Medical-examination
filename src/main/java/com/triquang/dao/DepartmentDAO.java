package com.triquang.dao;

import java.util.List;

import com.triquang.entity.Khoa;

public class DepartmentDAO extends JpaDAO<Khoa> implements GenericDAO<Khoa> {

	public DepartmentDAO() {
		
	}
	
	@Override
	public Khoa create(Khoa khoa) {
		return super.create(khoa);
	}

	@Override
	public Khoa update(Khoa khoa) {
		return super.update(khoa);
	}

	@Override
	public Khoa get(Object id) {
		return super.find(Khoa.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Khoa.class, id);
		
	}

	@Override
	public List<Khoa> listAll() {
		return super.findWithNamedQuery("Khoa.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Khoa.countAll");
	}

	public Khoa findByName(String khoaName) {
		List<Khoa> result = super.findWithNamedQuery("Khoa.findByName", "name", khoaName);
		
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	
}
