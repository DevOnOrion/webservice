package org.crazyit.cxf.ws.auth;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddHeadersInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	private String userId;
	private String userPass;
	
	public AddHeadersInterceptor(String userId, String userPass) {
		super(Phase.PREPARE_SEND);//在准备发送该SOAP消息的时候启用该拦截器
		this.userId = userId;
		this.userPass = userPass;
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		List<Header> headers = msg.getHeaders();
		Document doc = DOMUtils.createDocument();
		Element ele = doc.createElement("authHeader");
		
		//此处创建的元素应该按照服务器那边的要求。
		Element idEle = doc.createElement("userId");
		idEle.setTextContent(userId);
		Element passEle = doc.createElement("userPass");
		passEle.setTextContent(userPass);
		ele.appendChild(idEle);
		ele.appendChild(passEle);
		
		// 把ele元素包装成header,并添加到soap消息的header列表中
		headers.add(new Header(new QName("fkjava"), ele));
	}

	
}
