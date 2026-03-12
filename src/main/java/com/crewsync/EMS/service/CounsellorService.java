package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.CounsellorDTO;


public interface CounsellorService {

	    CounsellorDTO createCounsellor(CounsellorDTO counsellorDTO);

	    List<CounsellorDTO> getAllCounsellors();

	    CounsellorDTO getCounsellorById(Long id);

	    void deleteCounsellor(Long id);
}
