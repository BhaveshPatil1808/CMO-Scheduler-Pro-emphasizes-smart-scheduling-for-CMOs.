package com.Bhavesh.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CmDailyRoutineManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BCryptPasswordEncoder encode;
	@Test
	public void bPasswordGenerator() {
		System.out.println(encode.encode("admin123"));
	}

}
