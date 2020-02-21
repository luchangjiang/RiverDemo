/*
 *    Copyright (c) 2018-2025, River All rights reserved.
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
 * Author: River (River.lu@newtype.io)
 */

package com.river.SpringSecurityDemo.handler;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author River
 * @date 2018/10/8
 */
@Slf4j
@NoArgsConstructor
public class RiverAuthenticationFailHandler implements AuthenticationFailureHandler {

	/**
	 * 处理登录失败方法
	 * <p>
	 *
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
		log.info("用户：{} 登录失败，异常：{}", request.getParameter("username"), ex.getLocalizedMessage());
	}
}
