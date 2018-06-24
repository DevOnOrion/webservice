package org.fkjava.cxf.ws.auth;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//通过PhaseInterceptor可以指定拦截器在哪个阶段起作用
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	private static Log log = LogFactory.getLog(AuthInterceptor.class);
	
	public AuthInterceptor() {
		//super表示显式调用父类有参数的构造器。
		super(Phase.PRE_INVOKE);//该拦截器将会在调用之前拦截SOAP消息。
	}
	
	//实现自己的拦截器的时候，需要实现handleMessage方法
	//handleMessage方法中的形参就是被拦截到的Soap消息
	//一旦程序获得了Soap消息，剩下的事情就可以解析Soap消息，或者修改Soap消息。
	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		log.info("-------------"+msg);
		
		//得到soap消息的所有Header
		List<Header> headers = msg.getHeaders();
		//如果根本没有header
		if (headers == null || headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("根本没有header，别想调用了"));
		}
		//假如要求第一个Header携带了用户名、密码信息
		Header firstHeader = headers.get(0);
		Element ele = (Element) firstHeader.getObject();
		NodeList userIds = ele.getElementsByTagName("userId");
		NodeList userPasses = ele.getElementsByTagName("userPass");
		
		if(userIds.getLength() != 1){
			throw new Fault(new IllegalArgumentException("用户名的格式不对"));
		}
		if(userPasses.getLength() != 1){
			throw new Fault(new IllegalArgumentException("密码的格式不对"));
		}
		
		//得到第一个userId 元素里的文本内容，以此内容作为用户名
		String userId = userIds.item(0).getTextContent();
		String userPass = userPasses.item(0).getTextContent();
		
		//实际项目中应该去查询数据库，该用户名、密码是否被授权调用该WebService
		if(!userId.equals("fkjava")
				|| !userPass.equals("leegang")){
			throw new Fault(new IllegalArgumentException("用户名、密码不正确！")); 
		}
	}

	
}
