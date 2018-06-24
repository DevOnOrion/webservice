package org.crazyit.app.action;

import java.util.HashMap;
import java.util.Map;

import org.fkjava.cxf.ws.Cat;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return.Entry;
import org.fkjava.cxf.ws.HelloWorld;

import com.opensymphony.xwork2.ActionSupport;

public class ListCatsAction extends ActionSupport {

	private HelloWorld hw;//业务逻辑组件
	private Map<String,Cat> cats;

	public HelloWorld getHw() {
		return hw;
	}

	public Map<String, Cat> getCats() {
		return cats;
	}

	public void setCats(Map<String, Cat> cats) {
		this.cats = cats;
	}

	public void setHw(HelloWorld hw) {
		this.hw = hw;
	}
	
	public String execute() {
		//Action此处是调用了远程webservice代理的方法
		Return rtn = hw.getAllCats();
		Map<String, Cat> map = new HashMap<>();
		for (Entry entry : rtn.getEntry()) {
			map.put(entry.getKey(), entry.getValue());
		}
		this.setCats(map);
		return SUCCESS;
	}
}
