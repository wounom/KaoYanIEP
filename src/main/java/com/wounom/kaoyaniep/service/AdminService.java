package com.wounom.kaoyaniep.service;

import com.wounom.kaoyaniep.entity.Admin;
import com.wounom.kaoyaniep.entity.Result;
import com.wounom.kaoyaniep.entity.User;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:34
 */
public interface AdminService {
    boolean isAdminexsit(String email);

    Admin getAdminByUsername(String username);

    Boolean loginCheck(Admin admin,Admin newadmin);

    Result addAdmin(Admin admin);

    Result updateAdmin(Admin admin);
}
