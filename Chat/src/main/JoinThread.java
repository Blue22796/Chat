package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class JoinThread implements Runnable{
	Server server;
	
	public JoinThread(Server server) {
		this.server=server;
		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i<10;i++) {
			try {
				
				Socket client = server.server.accept();
				server.out[i]=new DataOutputStream(client.getOutputStream());
				ServerReceptionThread rt = new ServerReceptionThread(server,client);
				String clientIpAddress = ((InetSocketAddress)client.getRemoteSocketAddress()).getAddress()
					    .getHostAddress();
				System.out.println(clientIpAddress);
				new Thread(rt,"read").start();
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}
