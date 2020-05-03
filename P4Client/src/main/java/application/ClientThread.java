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
	
	GuessInfo game;

	ClientThread(Consumer<Serializable> call){
		callback = call;
	}

	public void run( int portNum) throws IOException {

		System.out.println( "inside the run function. port num is: " + portNum);
		try{
			sClient = new Socket("127.0.0.1", portNum);
			o = new ObjectOutputStream( sClient.getOutputStream());
			i = new ObjectInputStream( sClient.getInputStream());

			sClient.setTcpNoDelay(true);

		} catch( Exception e){
			System.out.println("Stream Wasnt Open");
			System.out.println("terminating...");
			sClient.close();
		}

		//Where client receives GuessInfo from server
		while( true){

			try{
				game = (GuessInfo) i.readObject();
				callback.accept(game);

			} catch( Exception e){ // TODO: logic for calculating unique PIDS
				System.out.println("Socket Issues");
			}

		
		}
	}

	//Where client sends GuessInfo to server
	public void send(GuessInfo data) {

		System.out.println("inside the send function.");
		try {
			o.reset();
			o.writeObject(data);
			o.reset();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
