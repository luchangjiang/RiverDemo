/*
 *
 *      Copyright (c) 2018-2025, River All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: River (River.lu@newtype.io)
 *
 */

package com.river.SpringSecurityDemo.endpoint;

import cn.hutool.core.util.StrUtil;
import com.river.SpringSecurityDemo.constant.CommonConstants;
import com.river.SpringSecurityDemo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author River
 * @date 2018/6/24
 * 删除token端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class RiverTokenEndpoint {
	private final TokenStore tokenStore;
	private final CacheManager cacheManager;
	private final PasswordEncoder passwordEncoder;
	/**
	 * 认证页面
	 *
	 * @param modelAndView
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require(ModelAndView modelAndView) {
		modelAndView.setViewName("ftl/login");
		return modelAndView;
	}

	/**
	 * 加密
	 *
	 * @param plainText  原文
	 * @return
	 */
	@GetMapping("/passwordEncoder/{plainText}")
	public R<String> getPasswordEncoder(@PathVariable String plainText){
		return R.ok(passwordEncoder.encode(plainText));
	}

	/**
	 * 退出token
	 *
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public R logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 为空").build();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 无效").build();
		}

		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		cacheManager.getCache("user_details").evict(auth2Authentication.getName());
		tokenStore.removeAccessToken(accessToken);
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 令牌管理调用
	 *
	 * @param token token
	 * @return
	 */
	@DeleteMapping("/{token}")
	public R<Boolean> delToken(@PathVariable("token") String token) {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
		tokenStore.removeAccessToken(oAuth2AccessToken);
		return new R<>();
	}

}
