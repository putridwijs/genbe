package com.putri.genbe.service;

import java.util.List;

import com.putri.genbe.dto.PendidikanDto;

public interface PendidikanService {
	List<PendidikanDto> savePendidikan(List<PendidikanDto> pendidikanDto, Integer idPerson);
}
