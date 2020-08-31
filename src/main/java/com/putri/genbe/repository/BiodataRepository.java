package com.putri.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Biodata;
import com.putri.genbe.entity.Person;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Integer> {
	List<Biodata> findAllByPersonIdPerson(Integer idPerson);

	Biodata findAllByPerson(Person person);
}
