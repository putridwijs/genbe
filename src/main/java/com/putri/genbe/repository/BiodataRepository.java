package com.putri.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Biodata;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, String>{
	List<Biodata> findAllByPersonIdPerson(String idPerson);
	
//	@Query(value = "SELECT p.nik, p.nama, p.alamat, b.nohp, b.tanggal_lahir, b.tempat_lahir FROM t_biodata b INNER JOIN t_person p ON b.idperson=p.id_person where p.nik = ?1", nativeQuery = true)
	
//	@Query(value = "SELECT * FROM t_person p INNER JOIN t_biodata b ON p.id_person=b.idperson where p.nik = ?1", nativeQuery = true)
//	Biodata cariNik(String nik);
	
//	List<Biodata> findByPerson(Person person);
	
}
