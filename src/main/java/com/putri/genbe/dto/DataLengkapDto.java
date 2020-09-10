package com.putri.genbe.dto;

public class DataLengkapDto {
    private String nama;
    private PendidikanDto data;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public PendidikanDto getData() {
        return data;
    }

    public void setData(PendidikanDto data) {
        this.data = data;
    }
}
