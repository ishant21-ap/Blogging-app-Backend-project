package com.project.blog.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blog.entites.TemporaryUser;
import com.project.blog.repositories.TempororyUserRepositories;
import com.project.blog.services.EmailService;
import com.project.blog.services.OtpVeficationService;

@Service
public class OtpVeficationServiceImpl implements OtpVeficationService{

	
	
	private Random random = new Random();
	
	@Autowired
	private TempororyUserRepositories repositories;
	
	@Autowired
	private EmailService emailService;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	
	
	
	@Override
	public boolean generateOtpAndSendEmail(TemporaryUser temporaryUser) {
		String otp = String.format("%04d", random.nextInt(10000));
		temporaryUser.setOtp(otp);
		repositories.save(temporaryUser);
		return emailService.sendOtp(temporaryUser.getEmail(), otp);
	}

	
	@Override
	public boolean verifyOtp(TemporaryUser temporaryUser) {
		Optional<TemporaryUser> existingUser = repositories.findByEmail(temporaryUser.getEmail());
		if(existingUser.isPresent() && existingUser.get().getOtp().equals(temporaryUser.getOtp())) {
			repositories.deleteById(temporaryUser.getEmail());
			return true;
		}
		return false;
	}

}
