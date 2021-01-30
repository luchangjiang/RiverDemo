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
 * Neither the name of the newtype.io developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: River (20207075@qq.com)
 */

package com.river.SpringSecurityDemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.SpringSecurityDemo.authentication.RiverAuthenticationProvider;
import com.river.SpringSecurityDemo.authentication.RiverUserNameAuthenticationFilter;
import com.river.SpringSecurityDemo.service.RiverUserDetailsService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author River
 * @date 2018/8/5
 * 手机号登录配置入口
 */
@Builder
public class RiverSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private ObjectMapper objectMapper;

	private AuthenticationSuccessHandler riverAuthenticationSuccessHandler;

	private AuthenticationFailureHandler riverAuthenticationFailHandler;

	private RiverUserDetailsService riverUserDetailsService;

	private PasswordEncoder passwordEncoder;

	public void configure(HttpSecurity http) {
		RiverUserNameAuthenticationFilter riverUserNameAuthenticationFilter = new RiverUserNameAuthenticationFilter();
		riverUserNameAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		riverUserNameAuthenticationFilter.setAuthenticationSuccessHandler(riverAuthenticationSuccessHandler);
		riverUserNameAuthenticationFilter.setAuthenticationFailureHandler(riverAuthenticationFailHandler);

		RiverAuthenticationProvider riverAuthenticationProvider = new RiverAuthenticationProvider();
		riverAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		riverAuthenticationProvider.setRiverUserDetailsService(riverUserDetailsService);

		http.authenticationProvider(riverAuthenticationProvider)
			.addFilterAfter(riverUserNameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
