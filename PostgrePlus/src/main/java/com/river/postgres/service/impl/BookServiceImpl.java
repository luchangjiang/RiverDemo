/*
 *    Copyright (c) 2018-2025, Eddid All rights reserved.
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
package com.river.postgres.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.river.postgres.entity.Book;
import com.river.postgres.mapper.BookMapper;
import com.river.postgres.service.BookService;
import org.springframework.stereotype.Service;

/**
 * dfd
 *
 * @author River
 * @date 2019-08-07 10:30:37
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
