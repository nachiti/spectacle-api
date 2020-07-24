package com.example.spectacle.service;

import com.example.spectacle.model.Admin;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.UserDetail;
import com.example.spectacle.model.Utilisateur;
import com.example.spectacle.repository.AdminRepository;
import com.example.spectacle.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    SpectacleService spectacleService;

    /**
     * recuper username, password et role de l'utilisateur et de l'Admin
     * @param username
     * @return
     */
    @Override
    public UserDetail findUserAndAdminByUsername(String username) {
        UserDetail userDetail = null;
         Utilisateur utilisateur = findUtilisateurByUsername(username);
         Admin admin = findAdminByUsername(username);
         if (utilisateur!=null){
             userDetail = new UserDetail(utilisateur.getUsername(),utilisateur.getPassword(),utilisateur.getRole());
         }else if (admin!=null){
             userDetail = new UserDetail(admin.getUsername(),admin.getPassword(),admin.getRole());
         }
    return userDetail;
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
        addUtilisateur(utilisateur1);
        Utilisateur utilisateur2 = new Utilisateur(
                "user2","321");
        addUtilisateur(utilisateur2);
    }

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        String hashPw = bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(hashPw);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur newUtilisateur, String username) {
         Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
         utilisateur.setUsername(newUtilisateur.getUsername());
         utilisateur.setPassword(newUtilisateur.getPassword());
        return addUtilisateur(utilisateur);
    }

    @Override
    public void deleteUtilisateur(String username) {
        utilisateurRepository.deleteByUsername(username);
    }

    @Override
    public Utilisateur findUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public Utilisateur addSpectacleToFavoris(String username, Long idSpectacle) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        Spectacle spectacle = spectacleService.getSpectaclesById(idSpectacle);
        utilisateur.addSpectacle(spectacle);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur deleteSpectacleFromFavoris(String username, Long idSpectacle) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        Spectacle spectacle = spectacleService.getSpectaclesById(idSpectacle);
        utilisateur.removeSpectacle(spectacle);
        return utilisateurRepository.save(utilisateur);
    }
}
