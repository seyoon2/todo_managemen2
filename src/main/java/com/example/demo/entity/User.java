package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "accountId", nullable = false, unique = true)
    private String accountId;

    @Column(name = "password", nullable = false, length =  20)
    private String password;

    /**
     * Getter
     */
    public int getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Setter
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *  Override Method
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
