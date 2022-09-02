package com.matthew.employeemanagementsystem.service.verificationtoken;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface VerificationService {
    void generateVerificationTokenAndSendVerificationMail(HttpServletRequest request, UserEntity userEntity);

    void verify(String tokenValue);
}
