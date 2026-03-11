package com.crewsync.EMS.service;

import com.crewsync.EMS.dto.AdminDTO;
import java.util.*;

public interface AdminService {

    AdminDTO createAdmin(AdminDTO adminDTO);

    List<AdminDTO> getAllAdmins();

    AdminDTO getAdminById(Long id);

    void deleteAdmin(Long id);

}
