package com.spring.acorn.aop.service;

import org.springframework.stereotype.Component;

@Component("target")
public class AopCoreImpl implements AopCore{

	@Override
	public String execute(String str) {
		// TODO Auto-generated method stub
		System.out.println("TARGET MESSAGE : " + str);
		return str;
	}

}
