package main;

import java.io.*;
import java.net.*;

public class Server {
	ServerSocket server;
	DataOutputStream[] out = new DataOutputStream[10];
	Socket[] clients = new Socket[10];
	JoinThread jt;
	
	public void start(int port) throws IOException {
		server = new ServerSocket(port);
		jt = new JoinThread(this);
		new Thread(jt,"join").start();
	}
	
	public void broadcast(String msg) {
		for(int i = 0; i<10; i++)
			try {
				out[i].writeUTF(msg);
			} catch (IOException e) {
				out[i]=null;
				clients[i]=null;
			}
		catch(NullPointerException e) {
			break;
		}
		
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		try {
			new Server().start(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
