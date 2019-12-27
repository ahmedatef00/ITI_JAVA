package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class chat_server {

	ServerSocket myServerSocket;
	DataInputStream dis ;
	PrintStream ps;
	

	static class ChatHandler extends Thread {
		DataInputStream dis;
		PrintStream ps;
		static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();
		
		public ChatHandler(Socket cs)
		{
			try {
				dis = new DataInputStream(cs.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps = new PrintStream(cs.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clientsVector.add(this);
			start();
		}
		
		public void run()
		{
			while(true){
				String str;
				try {
					str = dis.readLine();
					System.out.println(str);
					sendMessageToAll(str);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		void sendMessageToAll(String msg)
		{
			for(ChatHandler ch : clientsVector){
				ch.ps.println(msg);
			}
		}
	}
	
	public chat_server() {
		try {
			myServerSocket = new ServerSocket(5005);
			while (true) {
				Socket s = myServerSocket.accept();
				new ChatHandler(s);
			}
		} catch (Exception e) {
		
		}
	}
	
	public static void main(String[] args)
	{
		new chat_server();
	}
}
