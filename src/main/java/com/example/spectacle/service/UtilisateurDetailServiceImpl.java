package com.example.spectacle.service;

import com.example.spectacle.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UtilisateurDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UtilisateurService utilisateurService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.findUserByUsername(username);
        if(username == null) throw new UsernameNotFoundException("User Name is not Found :(");

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(utilisateur.getRole()));
        return new User(utilisateur.getUsername(),utilisateur.getPassword(),authorities);
    }

}
