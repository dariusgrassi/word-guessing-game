package application;

import java.io.ObjectInputStream;
import java.util.Random;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import java.io.*;

public class Server {

    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<>();
    TheServer server;
    private Consumer<Serializable> callback;

    GuessInfo game = new GuessInfo();
    int portNum;
    boolean serverStarted;

    boolean nextRoundPhase = false;

    //Server object constructor
    Server(Consumer<Serializable> call, int portNum) {
        this.portNum = 5555;

        callback = call;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread {

        //Loop runs as long as server is up
        public void run() {

            System.out.println("Server is now running on port " + portNum);
            serverStarted = true;

            try (ServerSocket mySocket = new ServerSocket(portNum)) {
                //Listen for client to connect to server
                while (true) {
                    //When client does connect, add them to our list of clients
                    ClientThread c = new ClientThread(mySocket.accept(), count);
                    System.out.println("client has connected to server: " + "client #" + count);

                    callback.accept("Client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();
                    
                    count++;
                }
            } catch (Exception e) {
                callback.accept("Server socket did not launch");
            }
        }
    }

    //Thread for each client is created each time a client joins
    //TODO: implement score tracker for ClientThread
    class ClientThread extends Thread {
        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        //Client thread constructor
        ClientThread(Socket s, int count) {
            this.connection = s;
            this.count = count;
        }

        //Where server sends GuessInfo to clients
        public void updateClients(String message) {
            for (ClientThread t : clients) {
                try {
                    t.out.reset();
                    t.out.writeObject(game);
                    t.out.reset();
                } catch (Exception e) {
                }
            }
        }
        public void updateClients(int client)
        {	try {
        		clients.get(client).out.reset();
        		clients.get(client).out.writeObject(game);
        		clients.get(client).out.reset();
        	} catch  (Exception e) {
        	}
        	
        }
        //Runs as long as client is connected
        public void run() {
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            } catch (Exception e) {
            }
            GuessInfo currentGame = new GuessInfo();
            while(true) {
                try {
                    game = (GuessInfo) in.readObject();
                    if(game.wordLength == 0) {
                    	int category = game.catChosen.get(count);
                    	Random rand = new Random();
                    	game.lineOfWord = rand.nextInt(50);
                    	if(category == 1) {
                    		File file = new File("music.txt");
                    		BufferedReader read = new BufferedReader(new FileReader(file));
                    		int i = 0;
                    		while(i < game.lineOfWord){
                    			i++;
                    			read.readLine();
                    		}
                    		game.wordLength = read.readLine().length();
                    		System.out.println(game.wordLength);
                    		game.remainingGuess = 6;
                    		game.correctWords = 0;
                    		game.correct = false;
                    		currentGame = game;
                    		//updateClients(game.clientNumber);
                    		
                    	}
                    	else if(category == 2) {
                    		File file = new File("animals.txt");
                    		BufferedReader read = new BufferedReader(new FileReader(file));
                    		int i = 0;
                    		while(i < game.lineOfWord){
                    			i++;
                    			read.readLine();
                    		}
                    		game.wordLength = read.readLine().length();
                    		System.out.println(game.wordLength);
                    		game.remainingGuess = 6;
                    		game.correctWords = 0;
                    		game.correct = false;
                    		currentGame = game;
                    	}
                    	else if(category == 3) {
                    		File file = new File("food.txt");
                    		BufferedReader read = new BufferedReader(new FileReader(file));
                    		int i = 0;
                    		while(i < game.lineOfWord){
                    			i++;
                    			read.readLine();
                    		}
                    		game.wordLength = read.readLine().length();
                    		System.out.println(game.wordLength);
                    		game.remainingGuess = 6;
                    		game.correctWords = 0;
                    		game.correct = false;
                    		currentGame = game;
                    	
                    	}
                    }
                    else {
                    	currentGame = game;
                    	
                    	//if(currentGame.guessedLetter==) {
                    		
               //     	}
                    	if(currentGame.guessedLetter == currentGame.wordLength) {
                    		currentGame.correct = true;
                    	}
                    }
                }

                catch(Exception e){
                    callback.accept("Client " + count + " disconnected from the game.");
                    clients.remove(this);
                    updateClients("Client #" + count + " has left the server!");
                    break;
                }
            }
        }
    }
}
