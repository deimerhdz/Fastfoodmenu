package com.dhernandez.fastfood.web.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dhernandez.fastfood.domain.dto.ErrorResponseDto;
import com.dhernandez.fastfood.domain.dto.ValidationErrorDto;
import com.dhernandez.fastfood.web.exceptions.UniqueException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler({UniqueException.class})
    public ResponseEntity<ErrorResponseDto> uniqueException(HttpServletRequest peticion, UniqueException ex){

        return buildResponse(peticion, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),null);
    }
	
	
	@ExceptionHandler({ConstraintViolationException.class})
	ResponseEntity<ErrorResponseDto> invalidDataException(HttpServletRequest request,ConstraintViolationException ex){
		   List<ValidationErrorDto> errors = new ArrayList<>();

	        for( ConstraintViolation violation :ex.getConstraintViolations() ) {
	            ValidationErrorDto err = new ValidationErrorDto(violation.getPropertyPath().toString(),violation.getMessage());
	            errors.add(err);
	        }
		return buildResponse(request,HttpStatus.BAD_REQUEST,"Error en la validacion de datos",errors);
	}
	
	ResponseEntity<ErrorResponseDto> buildResponse(HttpServletRequest request,HttpStatus status, String mensaje,List<ValidationErrorDto> errores ){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto();
        errorResponseDTO.setMessage(mensaje);
        errorResponseDTO.setStatusCode(status.value());
        errorResponseDTO.setPathUrl(request.getRequestURI());
        errorResponseDTO.setErrors(errores);
        return new ResponseEntity<>(errorResponseDTO,status);
    }

}
