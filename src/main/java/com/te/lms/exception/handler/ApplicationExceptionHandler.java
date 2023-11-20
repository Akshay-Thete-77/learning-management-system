package com.te.lms.exception.handler;

import static com.te.lms.constants.BatchConstants.BATCH_NOT_FOUND_WITH_ID;
import static com.te.lms.constants.EmployeeConstants.EMPLOYEE_NOT_FOUND_WITH_ID;
import static com.te.lms.constants.MentorConstants.MENTOR_NOT_FOUND_WITH_ID;
import static com.te.lms.constants.RequestConstants.REQUEST_NOT_FOUND_WITH_ID;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.NotDeletedException;
import com.te.lms.exception.RegistrationFailedException;
import com.te.lms.exception.RequestNotFoundException;
import com.te.lms.exception.UpdationFailedException;
import com.te.lms.response.ErrorResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handler(EmployeeNotFoundException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(EMPLOYEE_NOT_FOUND_WITH_ID+" "+exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ErrorResponse> handler(BatchNotFoundException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(BATCH_NOT_FOUND_WITH_ID+" "+exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MentorNotFoundException.class)
	public ResponseEntity<ErrorResponse> handler(MentorNotFoundException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(MENTOR_NOT_FOUND_WITH_ID+" "+exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotDeletedException.class)
	public ResponseEntity<ErrorResponse> handler(NotDeletedException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RegistrationFailedException.class)
	public ResponseEntity<ErrorResponse> handler(RegistrationFailedException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequestNotFoundException.class)
	public ResponseEntity<ErrorResponse> handler(RequestNotFoundException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(REQUEST_NOT_FOUND_WITH_ID+" "+exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UpdationFailedException.class)
	public ResponseEntity<ErrorResponse> handler(UpdationFailedException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().timeStamp(LocalDateTime.now()).error(exception.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}

}
