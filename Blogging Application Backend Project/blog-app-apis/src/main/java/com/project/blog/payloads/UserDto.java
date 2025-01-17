package com.project.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

	private int id;
	
	@NotEmpty   //(@NotNull + @NotBlank = @NotEmpty)
	@Size(min = 4, message = "Username must be of min 4 characters !!!")
	private String name;
	
	@Email(message = "Email address is not valid !!!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 characters and max of 10 characters !!")
	private String password;
	
	@NotEmpty
	private String about;
}
