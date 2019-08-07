/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.river.postgres.exception;

import com.river.postgres.enums.BaseResultEnum;
import com.river.postgres.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author river
 * @date 2019/6/17
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerResolver {

	/**
	 * 全局异常.
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handleGlobalException(Exception exception) {

		log.error("{} ex={}", HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
		return R.failed(BaseResultEnum.INTERNAL_SERVER_ERROR.getResultJsonString());
	}
	/**
	 * AccessDeniedException
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public R handleAccessDeniedException(AccessDeniedException exception) {
		log.error("{} ex={}", BaseResultEnum.UNAUTHORIZED.getResultModel().getCode(), exception.getLocalizedMessage());
		return R.failed(BaseResultEnum.UNAUTHORIZED.getResultJsonString());
	}
	/**
	 * validation Exception
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R handleBodyValidException(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		log.error("{} ex={}", BaseResultEnum.PARAM_VALID_ERROR.getResultModel().getCode(), fieldErrors.get(0).getDefaultMessage());
		return R.failed(BaseResultEnum.PARAM_VALID_ERROR.getResultJsonString());
	}
	/**
	 * ResourceNotFoundException
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public R resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		log.error("{} ex={}", BaseResultEnum.RESOURCE_NOT_FOUND.getResultModel().getCode(), exception.getLocalizedMessage());
		return R.failed(BaseResultEnum.RESOURCE_NOT_FOUND.getResultJsonString());
	}
	/**
	 * ParameterIllegalException
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler(ParameterIllegalException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R parameterIllegalExceptionHandler(ParameterIllegalException exception) {
		log.error("{} ex={}", BaseResultEnum.PARAM_ILLEGAL.getResultModel().getCode(), exception.getLocalizedMessage());
		return R.failed(BaseResultEnum.PARAM_ILLEGAL.getResultJsonString());
	}
}
