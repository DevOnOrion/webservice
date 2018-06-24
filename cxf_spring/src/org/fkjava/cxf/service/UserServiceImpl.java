package org.fkjava.cxf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fkjava.cxf.domain.Cat;
import org.fkjava.cxf.domain.User;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {

	//用hashmap来模拟内存中的数据库。
	static Map<User, List<Cat>> catDb = new HashMap<>();
	static{
		List<Cat> catList1 = new ArrayList<>();
		catList1.add(new Cat(1,"garfield","yellow"));
		catList1.add(new Cat(2,"机器猫","blue"));
		catDb.put(new User(1,"sun","3322","花果山"), catList1);
		
		List<Cat> catList2 = new ArrayList<>();
		catList2.add(new Cat(3,"kitty","purple"));
		catList2.add(new Cat(4,"熊猫","red"));
		catDb.put(new User(2,"zhu","5629","高老庄"), catList2);
	}
	
	@Override
	public List<Cat> getCatsByUser(User user) {

		return catDb.get(user);
	}

	@Override
	public Map<String, Cat> getAllCats() {
		Map<String, Cat> result = new HashMap<>();
		int i = 0;
		for ( List<Cat> cats : catDb.values()) {
			for (Cat cat : cats) {
				result.put("第"+i++ +"个", cat);
			}
		}
		return result;
	}

}
