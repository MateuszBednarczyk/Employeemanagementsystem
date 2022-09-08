package com.matthew.employeemanagementsystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 25, message = "Username has to be between 1 and 25 characters")
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 1, max = 100, message = "Email has to be between 1 and 100 characters")
    private String email;

    @NotNull
    private boolean isEnabled = false;

    @JsonIgnore
    @ManyToMany
    private List<DepartmentEntity> departments = new ArrayList<>();

    @JsonIgnore
    @NotNull
    private RoleType role;

    public UserEntity(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = RoleType.valueOf(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
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
        return this.isEnabled;
    }
}
