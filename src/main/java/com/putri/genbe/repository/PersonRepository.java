package com.putri.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findByNikLike(String nik);
}
