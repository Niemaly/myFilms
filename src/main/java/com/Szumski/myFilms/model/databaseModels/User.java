package com.Szumski.myFilms.model.databaseModels;

import com.Szumski.myFilms.model.AutoincrementId;
import com.Szumski.myFilms.repository.UserMoviesListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Long idFilmList;

    public Long getIdFilmList() {
        return idFilmList;
    }

    public void setIdFilmList(Long idFilmList) {
        this.idFilmList = idFilmList;
    }

    @Autowired
    public User(String userName, String password, String role) {
        this.username = userName;
        this.password = password;
        this.role = role;

        AutoincrementId idinc = new AutoincrementId();

        this.idFilmList = idinc.movieListRepo();
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
