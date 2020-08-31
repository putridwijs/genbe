package com.putri.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findByNik(String nik);
//	@Query(value = "SELECT * FROM t_person p INNER JOIN t_biodata b ON p.id_person=b.idperson where p.nik = ?1")
//	List<Person> findByNik(String nik);
}
