package application;

import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.function.Consumer;

public class ClientThread extends Thread {
	
	Socket sClient;

	ObjectOutputStream o;
	ObjectInputStream i;

	private Consumer<Serializable> callback;
	
	GuessInfo game;
	int portNum;

	public ClientThread(Consumer<Serializable> call, int port){
		this.callback = call;
		this.portNum = port;
		this.game = new GuessInfo();
	}

	public void run() {
		
		
		System.out.println( "inside the run function. port num is: " + portNum);
		try{
			sClient = new Socket("127.0.0.1", portNum);
			o = new ObjectOutputStream( sClient.getOutputStream());
			i = new ObjectInputStream( sClient.getInputStream());
			sClient.setTcpNoDelay(true);
			//Where client receives GuessInfo from server
			while( true){
				try{
					game = (GuessInfo) i.readObject();
					System.out.println("Froze here");
					callback.accept(game);
					if(game.end)
						break;

				} catch( Exception e){
					System.out.println("Socket Issues");
					// TODO: logic for calculating unique PIDS
					  // Do we actually need to do this, doesnt seem to be a good way on Java8
				  
					// I recommend these codes if needed 
//					String pid = ManagementFactory.getRuntimeMXBean().getName();
//					int splitMark = pid.indexOf("@");
//					if(splitMark != -1) {String pidNumbers = pid.substring(0, splitMark);}
				}
			
			}
			//TODO: Doesnt close correctly for some reason
			i.close();
			o.close();
			sClient.close();

		} catch( Exception e){
			System.out.println("Stream Wasnt Open");
			System.out.println("terminating...");
			//sClient.close();
		}

		

		
	}
	
	
	


	//Where client sends GuessInfo to server
	public void send(GuessInfo data) {

		System.out.println("inside the send function.");
		try {
			o.reset();
			o.writeObject(data);
			System.out.println("sent info");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
