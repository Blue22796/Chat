package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientUI {
	JFrame frame;
	JPanel panel;
	JPanel chat;
	JTextArea tField;
	JButton sendButton;
	Queue<String> messages;
	DataOutputStream out;
	String name;
	
	public ClientUI(String name,DataOutputStream out) {
		this.out = out;
		this.name=name;
		try {
			out.writeUTF(name+" has joined :D");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(516, 372);
		messages = new LinkedList<String>();
		build();
	}
	
	void build() {
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(0,0,0,255));
		chat = new JPanel(new GridBagLayout());
		tField = new JTextArea();
		tField.setPreferredSize(new Dimension(400,30));
		sendButton = new JButton("send");
		sendButton.setPreferredSize(new Dimension(100,30));
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;c.gridwidth=2;
		chat.setPreferredSize(new Dimension(400,300));
		c.gridy=0;
		c.gridy=0;
		panel.add(chat,c);
		c.gridy=1;
		c.gridwidth=1;
		panel.add(tField,c);
		c.gridx=1;
		panel.add(sendButton,c);
		frame.add(panel);
		frame.setVisible(true);
		
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					out.writeUTF(name+": "+tField.getText());
					System.out.println("Sent!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tField.setText("");
			}
			
		});
	}
	
	public void add(String msg) {
		messages.add(msg);
		if(messages.size()>10)
			messages.remove();
	}

	public void refresh() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;c.gridwidth=2;
		chat.removeAll();
		for(String msg:messages) {
			c.gridy=c.gridy+1;
			JLabel x = new JLabel(msg);
			x.setPreferredSize(new Dimension(480,30));
			chat.add(x,c);
		}
		frame.revalidate();
	}
}
