package com.ngo.skyfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class SkyfoundationApplicationTests {

	@Test
	void contextLoads() {

		assertNotNull(SpringApplication.run(SkyfoundationApplication.class));
	}

}
