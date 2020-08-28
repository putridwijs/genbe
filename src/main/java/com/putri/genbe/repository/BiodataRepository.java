package com.putri.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putri.genbe.entity.Biodata;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, String>{
//	@Query(value = "SELECT p.nik, p.nama, p.alamat, b.nohp, b.tanggal_lahir, b.tempat_lahir FROM t_biodata b INNER JOIN t_person p ON b.idperson=p.id_person where p.nik = ?1", nativeQuery = true)
//	Biodata cariNik(String nik);
	
//	List<Biodata> findByPerson(Person person);
	
}
