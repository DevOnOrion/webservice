package org.fkjava.cxf.ws;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.fkjava.cxf.domain.Cat;
import org.fkjava.cxf.domain.User;

@WebService
public interface HelloWorld {

	String sayHi(String name);
	
	List<Cat> getCatsByUser(User user);
	
	Map<String, Cat> getAllCats();
}
