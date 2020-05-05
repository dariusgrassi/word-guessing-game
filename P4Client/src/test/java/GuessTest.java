import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import application.ClientThread;
import application.GuessInfo;

class GuessTest {
	static GuessInfo newGame;
	static ClientThread client;
	
	@BeforeEach
	void setup() {
		newGame = new GuessInfo();
	}
	
	@Test
	void test() {
		assertEquals("GuessInfo", newGame.getClass().getName());
		
	}
	
	@Test
	void test2() {
		assertEquals(false, newGame.end);
	}
	
	@Test
	void test3() {
		assertEquals(0, newGame.wordLength);
	}
	
	@Test
	void test4() {
		assertEquals(0, newGame.correctWords);
	}
	
	@Test
	void test5() {
		assertEquals(6, newGame.remainingGuess);
	}

}
