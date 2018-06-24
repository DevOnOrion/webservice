package lee;

import javax.xml.ws.Endpoint;

import org.fkjava.cxf.ws.HelloWorld;
import org.fkjava.cxf.ws.impl.HelloWorldWs;

public class ServiceMain {

	public static void main(String[] args) {
		
		HelloWorld hw = new HelloWorldWs();
		//调用EndPoint的publish方法发布Web Service
		Endpoint.publish("http://127.0.0.1:9999/fkjava", hw);
		
		System.out.println("webservice暴露成功!");
	}
}
