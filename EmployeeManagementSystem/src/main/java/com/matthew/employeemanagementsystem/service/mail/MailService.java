package com.matthew.employeemanagementsystem.service.mail;

public interface MailService {
    void sendEmail(String directionMail, String mailSubject, String mailText);
}
