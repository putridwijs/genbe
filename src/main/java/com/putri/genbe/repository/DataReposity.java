package com.putri.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Person;

@Repository
public interface DataReposity extends JpaRepository<Person, Integer> {

}
