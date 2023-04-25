package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceptionThread implements Runnable{
	Server server;
	Socket c;
	DataInputStream in;
	
	ServerReceptionThread(Server server,Socket c) throws IOException{
		this.server=server;
		this.c=c;
		in=new DataInputStream(c.getInputStream());
	}
	
	@Override
	public void run() {
		while(true)
			try {
				server.broadcast(in.readUTF());
			} catch (IOException e) {
			}
	}
	

}
