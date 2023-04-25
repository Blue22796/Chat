package main;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientReceptionThread implements Runnable{
	Client client;
	DataInputStream dis;
	
	public ClientReceptionThread(Client client) {
		this.client=client;
		try {
			this.dis = new DataInputStream(client.cnc.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true)
			try {
				client.add(dis.readUTF());
			} catch (IOException e) {
				break;
			}	
	}
	
	
}
