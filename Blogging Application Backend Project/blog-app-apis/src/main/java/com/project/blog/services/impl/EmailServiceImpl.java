package com.project.blog.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.blog.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendOtp(String email, String otp) {
        try {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Email Verification OTP");
            simpleMailMessage.setText("Your one-time password for verification is: " + otp);

            javaMailSender.send(simpleMailMessage);
            logger.info("Email sent successfully to {}", email);
            return true;

        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", email, e.getMessage(), e);
            return false;
        }
    }
}
