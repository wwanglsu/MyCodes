package rpc;

import java.io.IOException;

import org.apache.hadoop.ipc.VersionedProtocol;

public class MyBiz implements MyBizable{

	/* (non-Javadoc)
	 * @see rpc.MyBizable#hello(java.lang.String)
	 */
	@Override
	public String hello(String name){
		return "Hello" + name;
	}
	
	@Override
	public long getProtocolVersion(String protocol, long clientVersion) throws IOException{
		return MyBizable.VERSION;
	}
}
