package com.putri.genbe.dto;

public class PendidikanDto {
	private Integer idPerson;
	private String jenjang;
	private String institusi;
	private String tahunMasuk;
	private String tahunLulus;
	
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
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

	public String getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}

	public String getTahunLulus() {
		return tahunLulus;
	}

	public void setTahunLulus(String tahunLulus) {
		this.tahunLulus = tahunLulus;
	}

}
