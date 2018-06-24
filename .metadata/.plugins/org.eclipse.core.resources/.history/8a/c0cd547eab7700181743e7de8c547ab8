package lee;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.crazyit.cxf.ws.auth.AddHeadersInterceptor;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return;
import org.fkjava.cxf.ws.GetAllCatsResponse.Return.Entry;
import org.fkjava.cxf.ws.HelloWorld;
import org.fkjava.cxf.ws.impl.HelloWorldWs;

public class ClientMain {

	public static void main(String[] args) {

		HelloWorldWs factory = new HelloWorldWs();
		HelloWorld hw = factory.getHelloWorldWsPort();
		
		Client client = ClientProxy.getClient(hw);
		client.getOutInterceptors().add(new AddHeadersInterceptor("fkjava", "leegang"));
		
		System.out.println(hw.sayHi("豬八戒"));
		
		Return cats = hw.getAllCats();
		for (Entry entry : cats.getEntry()) {
			System.out.println(entry.getKey()+"-->"+entry.getValue().getName());
		} 
	}

}
