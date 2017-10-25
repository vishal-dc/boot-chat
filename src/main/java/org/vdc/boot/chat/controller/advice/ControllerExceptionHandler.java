package org.vdc.boot.chat.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.vdc.boot.chat.controller.UserController;
import org.vdc.boot.chat.exception.UserNotFoundException;

@ControllerAdvice(basePackageClasses = UserController.class)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		return new ResponseEntity<>(new ClientError(HttpStatus.NOT_FOUND.toString(), ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	ResponseEntity<?> handleGenericException(HttpServletRequest request, Throwable ex) {
		return new ResponseEntity<>(new ClientError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// private HttpStatus getStatus(HttpServletRequest request) {
	// Integer statusCode = (Integer)
	// request.getAttribute("javax.servlet.error.status_code");
	// if (statusCode == null) {
	// return HttpStatus.INTERNAL_SERVER_ERROR;
	// }
	// return HttpStatus.valueOf(statusCode);
	// }

}