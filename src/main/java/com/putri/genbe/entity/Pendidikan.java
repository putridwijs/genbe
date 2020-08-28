package com.putri.genbe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pendidikan")
public class Pendidikan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pendidikan")
	private Integer idPendidikan;
	@Column(name = "jenjang", length = 10, nullable = false)
	private String jenjang;
	@Column(name = "institusi", length = 50, nullable = false)
	private String institusi;
	@Column(name = "tahunmasuk", length = 10, nullable = false)
	private String masuk;
	@Column(name = "tahunlulus", length = 10, nullable = false)
	private String lulus;

	@ManyToOne
	@JoinColumn(name = "idperson", nullable = false)
	private Person person;

	public Integer getIdPendidikan() {
		return idPendidikan;
	}

	public void setIdPendidikan(Integer idPendidikan) {
		this.idPendidikan = idPendidikan;
	}

	public String getJenjang() {
		return jenjang;
	}

	public void setJenjang(String jenjang) {
		this.jenjang = jenjang;
	}

	public String getInstitusi() {
		return institusi;
	}

	public void setInstitusi(String institusi) {
		this.institusi = institusi;
	}

	public String getMasuk() {
		return masuk;
	}

	public void setMasuk(String masuk) {
		this.masuk = masuk;
	}

	public String getLulus() {
		return lulus;
	}

	public void setLulus(String lulus) {
		this.lulus = lulus;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
