package com.matthew.employeemanagementsystem.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String directionMail, String mailSubject, String mailText) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(directionMail);
        simpleMailMessage.setSubject(mailSubject);
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }
}
