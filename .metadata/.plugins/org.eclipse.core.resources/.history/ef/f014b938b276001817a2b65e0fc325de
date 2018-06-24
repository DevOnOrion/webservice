package lee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.fkjava.cxf.ws.HelloWorld;
import org.fkjava.cxf.ws.impl.HelloWorldWs;

public class ServiceMain {

	public static void main(String[] args) throws Exception {
		
		HelloWorld hw = new HelloWorldWs();
		//调用EndPoint的publish方法发布Web Service
		EndpointImpl ep = (EndpointImpl) Endpoint.publish("http://127.0.0.1:9999/fkjava", hw);
		
		//添加In拦截器
		ep.getInInterceptors().add(new LoggingInInterceptor());
		ep.getOutInterceptors().add(new LoggingInInterceptor());
		System.out.println("webservice暴露成功!");
	}
}
