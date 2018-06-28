package com.xuhailiang5794.ik;

import com.xuhailiang5794.ik.test.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IkApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private HelloSender helloSender;

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			helloSender.send("hello world!" + i);
		}
	}

}
