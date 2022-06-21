package com.triquang.dao;

import java.util.List;

import com.triquang.entity.Lichkham;

public class ExaminationScheduleDAO extends JpaDAO<Lichkham> implements GenericDAO<Lichkham> {
	
	public ExaminationScheduleDAO() {
	}

	@Override
	public Lichkham create(Lichkham lichkham) {
		return super.create(lichkham);
	}

	@Override
	public Lichkham update(Lichkham lichkham) {
		return super.update(lichkham);
	}

	@Override
	public Lichkham get(Object scheduleId) {
		return super.find(Lichkham.class, scheduleId);
	}

	@Override
	public void delete(Object scheduleId) {
		super.delete(Lichkham.class, scheduleId);
	}

	@Override
	public List<Lichkham> listAll() {		
		return super.findWithNamedQuery("Lichkham.findAll");
	}

	public Lichkham findByTitle(String title) {
		List<Lichkham> result = super.findWithNamedQuery("Lichkham.findByTitle", "title", title);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Lichkham> listByDoctor(int doctorId) {
		return super.findWithNamedQuery("Lichkham.findByDoctor", "docId", doctorId);
	}
	
	public List<Lichkham> listByPatient(int patientId) {
		return super.findWithNamedQuery("Lichkham.findByPatient", "patientId", patientId);
	}
	
	public List<Lichkham> search(String keyword) {
		return super.findWithNamedQuery("Lichkham.search", "keyword", keyword);
	}
	
	public List<Lichkham> listNewSchedules() {		
		return super.findWithNamedQuery("Lichkham.listNew", 0, 4);
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("Lichkham.countAll");
	}

	public long countBySchedule(int doctorId) {
		return super.countWithNamedQuery("Lichkham.countBySchedule", "docId", doctorId);
	}
	
}
