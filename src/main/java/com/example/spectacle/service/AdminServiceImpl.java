package com.example.spectacle.service;

import com.example.spectacle.model.Admin;
import com.example.spectacle.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void initAdmin() {
        Admin admin1 = new Admin("admin","123");
        addAdmin(admin1);
        Admin admin2 = new Admin("admin2","321");
        addAdmin(admin2);

    }

    @Override
    public Admin addAdmin(Admin admin) {
        String hashPWD = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(hashPWD);
        return adminRepository.save(admin);
    }

    @Override
    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
