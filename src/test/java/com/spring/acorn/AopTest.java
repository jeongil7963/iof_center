package com.spring.acorn;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.acorn.aop.service.AopCore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AopTest {
	
	@Resource(name="target")
	private AopCore core;
	
	@Test
	public void beforeAopTest(){
		String msg = core.execute("호출");
		System.out.println("result : " + msg);
	}
}
