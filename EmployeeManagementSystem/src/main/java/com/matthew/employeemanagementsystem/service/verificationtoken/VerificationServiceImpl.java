package com.matthew.employeemanagementsystem.service.verificationtoken;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.entities.VerificationTokenEntity;
import com.matthew.employeemanagementsystem.repository.VerificationTokenRepository;
import com.matthew.employeemanagementsystem.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    private static String generateTokenValue() {
        return UUID.randomUUID().toString();
    }

    private String generateVerificationLink(HttpServletRequest request, String tokenValue) {
        return "http://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/api/users/verify?tokenValue=" + tokenValue;
    }

    @Override
    public void generateVerificationTokenAndSendVerificationMail(HttpServletRequest request, UserEntity userEntity) {
        String tokenValue = generateTokenValue();
        VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(tokenValue, userEntity);
        sendVerificationLink(generateVerificationLink(request, tokenValue), userEntity.getEmail());
        verificationTokenRepository.save(verificationTokenEntity);
    }

    @Override
    public void verify(String tokenValue) {
        verificationTokenRepository.findByTokenValue(tokenValue).getUserEntity().setEnabled(true);
    }

    public void sendVerificationLink(String verificationLink, String userEmail) {
        mailService.sendEmail(userEmail, "HELLO", verificationLink);
    }
}
