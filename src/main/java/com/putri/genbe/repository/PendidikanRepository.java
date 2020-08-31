package com.putri.genbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Pendidikan;
import com.putri.genbe.entity.Person;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan, Integer> {
	Pendidikan findAllByPerson(Person person);

	@Query(value = "SELECT jenjang FROM public.t_pendidikan where idperson =?1 order by tahunlulus desc limit 1", nativeQuery = true)
	public String cariJenjangPendidikan(Integer idPerson);
}
