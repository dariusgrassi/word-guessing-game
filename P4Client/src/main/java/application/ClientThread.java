package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.function.Consumer;

public class ClientThread extends Thread {
	
	Socket sClient;

	ObjectOutputStream o;
	ObjectInputStream i;

	private Consumer<Serializable> callback;

	ClientThread(Consumer<Serializable> call){
		callback = call;
	}

	public void run( int portNum) throws IOException {

		System.out.println( "inside the run function. port num is: " + portNum);
		try{
			sClient = new Socket( "127.0.0.1", portNum);
			o = new ObjectOutputStream( sClient.getOutputStream());
			i = new ObjectInputStream( sClient.getInputStream());
			

		} catch( Exception e){
			System.out.println("Stream Wasnt Open");
			System.out.println("terminating...");
			sClient.close();
		}
		
		while( true){

			try{
				
				String message = i.readObject().toString();
				callback.accept(message);
				

			} catch( Exception e){
				System.out.println("Socket Issues");
			}
		}
	}

	public void send(String data) {

		System.out.println("inside the send function.");
		try {
			o.writeObject(data);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
