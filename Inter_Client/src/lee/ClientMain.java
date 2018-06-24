package lee;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.fkjava.cxf.ws.Cat;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return.Entry;
import org.fkjava.cxf.ws.HelloWorld;
import org.fkjava.cxf.ws.User;
import org.fkjava.cxf.ws.impl.HelloWorldWs;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		HelloWorldWs factory = new HelloWorldWs();
		//此处返回的只是web
		HelloWorld hw = factory.getHelloWorldWsPort();
		
		Client client = ClientProxy.getClient(hw);
		client.getInInterceptors().add(new LoggingInInterceptor());
		client.getOutInterceptors().add(new LoggingOutInterceptor());
		
		System.out.println(hw.sayHi("孙悟空"));
		Class<?> forName = Class.forName("org.fkjava.cxf.ws.User");
		User user = (User) forName.newInstance();
		user.setId(30);
		user.setName("sun");
		user.setPassword("3322");
		List<Cat> list = hw.getCatsByUser(user);
		for (Cat cat : list) {
			System.out.println(cat.getName());
		}
		Return allCats = hw.getAllCats();
		List<Entry> list2 = allCats.getEntry();
		for (Entry entry : list2) {
			System.out.println(entry.getKey()+entry.getValue().getName());
		}
	}
}
