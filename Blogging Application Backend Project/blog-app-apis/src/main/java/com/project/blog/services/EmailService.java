package com.project.blog.services;

public interface EmailService {

	boolean sendOtp(String email, String otp);
}
