package chat;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat_client extends JFrame {

	Socket mySocket;
	DataInputStream dis ;
	PrintStream ps;
	
	public Chat_client() {
		this.setLayout(new FlowLayout());
		JTextArea text_chat=new JTextArea(15, 32);
		JScrollPane scroll=new JScrollPane(text_chat);
		scroll.setViewportView(text_chat);
		JTextField mess=new JTextField(32);
		JButton B_send=new JButton("Send");
		
		
		// add components
		add(text_chat);
		add(mess);
		add(B_send);
		
		// handle socket
		try
		{
			mySocket = new Socket("127.0.0.1", 7996);
			dis = new DataInputStream(mySocket.getInputStream ());
			ps = new PrintStream(mySocket.getOutputStream ());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true)
					{
						try {
							text_chat.append(dis.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
		}
		//end of try
		
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		//send Action Performed
		
		B_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ps.println(mess.getText());
				ps.println("\n");
				mess.setText("");
			}
		});
		
		try{
			ps.close();
			dis.close();
			mySocket.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		Chat_client my_chat=new Chat_client();
		my_chat.setSize(400, 350);
		my_chat.setLocationRelativeTo(null);
		my_chat.setVisible(true);
		my_chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
