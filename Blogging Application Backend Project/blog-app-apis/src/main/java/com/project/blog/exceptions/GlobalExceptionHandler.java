package com.project.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.blog.payloads.ApiResponse;

//This annotation has all method which are required to handle expection
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//Now whenever we get any resourceNotFoundException (custom exception that we have created) this method will be 
	//called, after this what user will get is Whatever is message of apiresponse with status code
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
} 
