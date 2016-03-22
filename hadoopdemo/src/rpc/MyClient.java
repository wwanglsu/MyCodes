package rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class MyClient {
	public static void main(String... args) throws IOException{
		final MyBizable proxy = (MyBizable)RPC.waitForProxy(MyBizable.class,
				MyBizable.VERSION,
				new InetSocketAddress(MyServer.SERVER_ADDRESS, MyServer.SERVER_PORT), 
				new Configuration());
		
		final String result= proxy.hello(" Stanly.");
		System.out.println("Client call result:  "+result);
		
		RPC.stopProxy(proxy);
	}
}
