package org.fkjava.cxf.service;

import java.util.List;
import java.util.Map;

import org.fkjava.cxf.domain.Cat;
import org.fkjava.cxf.domain.User;

public interface UserService {

	List<Cat> getCatsByUser(User user);

	Map<String, Cat> getAllCats();

}
