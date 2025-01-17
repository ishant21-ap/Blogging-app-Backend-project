package com.project.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.entites.TemporaryUser;
import com.project.blog.services.OtpVeficationService;

@RestController
@RequestMapping("/api/verify")
public class OtpVerificationController {
	
	
	@Autowired
	private OtpVeficationService otpVeficationService;
	
	
	@PostMapping("/otpSend")
	public ResponseEntity<String> sendOtp(@RequestBody TemporaryUser temporaryUser){
		boolean isSent = otpVeficationService.generateOtpAndSendEmail(temporaryUser);
		if(isSent) {
			return ResponseEntity.status(HttpStatus.OK).body("Otp sent Successfully !");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sent otp ! Please try again later.");
		}
	}
	
	
	@PostMapping("/verifyOtp")
	public ResponseEntity<String> verifyOtp(@RequestBody TemporaryUser temporaryUser){
		boolean isVerified = otpVeficationService.verifyOtp(temporaryUser);
		if(isVerified) {
			return ResponseEntity.status(HttpStatus.OK).body("OTP Verified Successfully !");
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP or email ! Verification failed !");
		}
	}

}
