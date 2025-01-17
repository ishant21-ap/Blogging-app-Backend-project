package com.project.blog.services;

import com.project.blog.entites.TemporaryUser;

public interface OtpVeficationService {
	
	
	boolean generateOtpAndSendEmail(TemporaryUser temporaryUser);
	
	boolean verifyOtp(TemporaryUser temporaryUser);
	
}
