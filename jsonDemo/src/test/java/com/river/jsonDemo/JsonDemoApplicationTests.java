package com.river.jsonDemo;

import com.river.jsonDemo.bean.Query;
import com.river.jsonDemo.service.JsonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonDemoApplicationTests {
	@Autowired
	private JsonService jsonService;

	@Test
	public void contextLoads() throws IOException {
		List<Query> queryList = jsonService.test();
		Assert.assertNotNull(queryList);
	}

}
