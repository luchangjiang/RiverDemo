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
 *  Neither the name of the newtype.io developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: River (20207075@qq.com)
 *
 */

package com.river.SpringSecurityDemo.util;

import com.river.SpringSecurityDemo.constant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author River
 */
@Builder
@ToString
@Accessors(chain = true)
@ApiModel(description = "响应信息主体")
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回标记：成功标记=200，失败标记=500")
	private int code = CommonConstants.SUCCESS;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回信息")
	private String msg = "success";

	@Getter
	@Setter
	@ApiModelProperty(value = "数据")
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}

	public R(int code, String msg) {
		this(code, msg, null);
	}

	public R(int code, String msg, T data) {
		this.code = code;
		if (msg != null) {
			this.msg = msg;
		}
		this.data = data;
	}

	public static <T> R<T> ok(T data) {
		return new R<T>(CommonConstants.SUCCESS, "common.request.success", data);
	}

	public static R<Void> fail(int code, String msg) {
		return new R<Void>(code, msg, null);
	}

	public static R<Void> fail(String msg) {
		return fail(CommonConstants.FAIL, msg);
	}

	public boolean isSuccess() {
		return code == 200;
	}

	@Override
	public String toString() {
		return "R{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
