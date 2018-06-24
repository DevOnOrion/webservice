package org.fkjava.cxf.ws.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.fkjava.cxf.domain.Cat;
import org.fkjava.cxf.domain.User;
import org.fkjava.cxf.service.UserService;
import org.fkjava.cxf.ws.HelloWorld;

@WebService(endpointInterface="org.fkjava.cxf.ws.HelloWorld"
		,serviceName="helloWorldWs")
public class HelloWorldWs implements HelloWorld {

	private UserService us;
	
	public void setUs(UserService us) {
		this.us = us;
	}

	@Override
	public String sayHi(String name) {
		return name+"您好"
			+"现在时间是："+new Date();
	}

	@Override
	public List<Cat> getCatsByUser(User user) {
		// 在实际的项目中，WebService组件自己并不会去实现业务功能
		// 它只是调用业务逻辑组件的方法来暴露Web Service
		return us.getCatsByUser(user);
	}

	@Override
	public Map<String, Cat> getAllCats() {
		return us.getAllCats();
	}

}
