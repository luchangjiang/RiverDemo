package com.river.mybatis.sensitive;


import org.springframework.cglib.core.internal.Function;

/**
 * 具体策略的函数
 * @author felord.cn
 * @since 11:24
 **/
public interface Desensitizer extends Function<String,String> {
}