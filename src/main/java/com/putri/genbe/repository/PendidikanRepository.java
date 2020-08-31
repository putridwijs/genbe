package com.putri.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.Person;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan, Integer> {
	Pendidikan findAllByPerson(Person person);
	
//	@Query(value = "SELECT jenjang FROM t_pendidikan p where p.tahunlulus = ")
//	Pendidikan findByJenjang (String idPerson);
}
