package com.project.blog.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryUser {
	
	@Id
	@NotEmpty(message = "Email-is required !!!")
	@Email(message = "Invalid Email-id !!!")
	private String email;
	
	private String otp;

}
