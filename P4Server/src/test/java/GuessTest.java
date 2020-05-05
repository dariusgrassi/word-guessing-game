import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessTest {

	Server serverConnection;
	
	@BeforeEach
	void createServer() {
		serverConnection = new Server(data -> {

		//Infinite loop for checking if information has been updated
		Platform.runLater(()->{
			game = serverConnection.game;
		});

		}, 5555);
	}

	@Test
	void testGame(){
		assertNotNull(serverConnection.game);
	}
	
	@Test
	void testServerStarted(){
		assertTrue(serverConnection.serverStarted);	
	}
	
	@Test
	void testServerPort(){
		assertEquals(5555, serverConnection.portNum);	
	}
	
	@Test
	void testTheServerInstance(){
		assertNotNull(serverConnection.server);	
	}
	
	@Test
	void testNoClientsYet(){
		assertEquals(0, serverConnection.clients.size());	
	}
}
