package com.example.spectacle.service;

import com.example.spectacle.model.Admin;

public interface AdminService {

    void initAdmin();

    Admin addAdmin(Admin admin);
    Admin findAdminByUsername(String username);
}
