package com.river.PostgreSql;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgreSqlApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	BasicTextEncryptor encryptor;

	@Test
	public void getPass() {
		encryptor.setPassword("dev");
		//用户名
		String name = encryptor.encrypt("root");
		//密码
		String password = encryptor.encrypt("123456");
		System.out.println(name);
		System.out.println(password);
	}
}
