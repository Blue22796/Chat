package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	Socket cnc;
	String name;
	ClientUI ui;
	
	public Client(String name, int port) throws IOException {
		this.cnc=new Socket("41.45.85.99",port);
		this.name=name;
		ui=new ClientUI(name,new DataOutputStream(cnc.getOutputStream()));
		new Thread(new ClientReceptionThread(this)).start();
	}
	
	
	public void add(String msg) {
		ui.add(msg);
		ui.refresh();
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your name :)");
		String name = scan.next();
		scan.close();
		try {
			new Client(name,8080);
		}
		catch (IOException e) {
			System.out.println("Omar is not available atm D:");
		}
	}
}
