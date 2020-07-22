package com.example.spectacle.service;

import com.example.spectacle.model.Admin;
import com.example.spectacle.model.UserDetail;
import com.example.spectacle.model.Utilisateur;
import com.example.spectacle.repository.AdminRepository;
import com.example.spectacle.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AdminRepository adminRepository;

    /**
     * recuper username, password et role de l'utilisateur et de l'Admin
     * @param username
     * @return
     */
    @Override
    public UserDetail findUserAndAdminByUsername(String username) {
        return utilisateurRepository.findUserAndAdminByUsername(username);
    }

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

    @Override
    public void initUtilisateur() {
        Utilisateur utilisateur1 = new Utilisateur(
                "user1","123");
        addUtlisisateur(utilisateur1);
        Utilisateur utilisateur2 = new Utilisateur(
                "user2","321");
        addUtlisisateur(utilisateur2);
    }

    @Override
    public Utilisateur addUtlisisateur(Utilisateur utilisateur) {
        String hashPw = bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(hashPw);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findUserByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public List<Utilisateur> getAllSpectacleFavorisOfUtilisateur(Long idUser) {
        return null;
    }

    @Override
    public Utilisateur addSpectacleToFavoris(Long idUser, Long idSpectacle) {
        return null;
    }

    @Override
    public void deleteSpectacleFromFavoris(Long idUser, Long idSpectacle) {

    }
}
