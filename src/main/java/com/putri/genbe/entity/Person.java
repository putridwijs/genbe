package com.putri.genbe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Integer idPerson;
	@Column(name = "nik", unique = true, length = 16, nullable = false)
	private String nik;
	@Column(name = "nama", length = 50)
	private String nama;
	@Column(name = "alamat")
	private String alamat;

	@OneToOne(mappedBy = "person")
	private Biodata biodata;

	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Biodata getBiodata() {
		return biodata;
	}

	public void setBiodata(Biodata biodata) {
		this.biodata = biodata;
	}

}
