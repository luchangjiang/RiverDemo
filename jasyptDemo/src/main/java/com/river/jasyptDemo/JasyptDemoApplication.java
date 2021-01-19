package com.river.jasyptDemo;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasyptDemoApplication {
	public static void main(String[] args) {

//		SpringApplication.run(JasyptDemoApplication.class, args);

		String salt  = "pigx";
		BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
		//加密所需的salt(盐)
		basicTextEncryptor.setPassword(salt);
		//要加密的数据（数据库的用户名或密码）
		String username = basicTextEncryptor.encrypt("sales");
		String password = basicTextEncryptor.encrypt("123456");
		System.out.println("basic========================");
		System.out.println("client-id:"+username);
		System.out.println("client-secret:"+password);
		System.out.println("\r\n");
		StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();

		strongTextEncryptor.setPassword(salt);
		//要加密的数据（数据库的用户名或密码）
		username = strongTextEncryptor.encrypt("root");
		password = strongTextEncryptor.encrypt("123456");
		System.out.println("strong========================");
		System.out.println("client-id:"+username);
		System.out.println("client-secret:"+password);
		System.out.println("\r\n");

		System.out.println("properties========================");
		System.out.println("client-id:" + getClientConfig().getString("clientId"));
		System.out.println("client-secret:" +getClientConfig().getString("clientSecret"));
	}

    private static Configuration getClientConfig(){
		try {
			return new PropertiesConfiguration("client.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("获取配置文件失败，", e);
		}
	}
}
