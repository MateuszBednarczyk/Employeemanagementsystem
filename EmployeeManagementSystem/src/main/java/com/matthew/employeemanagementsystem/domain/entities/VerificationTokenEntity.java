package com.matthew.employeemanagementsystem.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
@Data
@NoArgsConstructor
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenValue;

    @OneToOne
    private UserEntity user;

    public VerificationTokenEntity(String tokenValue, UserEntity user) {
        this.tokenValue = tokenValue;
        this.user = user;
    }
}
