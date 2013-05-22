package trivia;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class GameTest {

	protected PrintStream printStreamMock = Mockito.mock(PrintStream.class);

	@After
	public void verifyOut() {
		
	}
	

	@Test
	public void shouldWin() throws Exception {
		Game game = createGame();
		
		game.add("T");
		
		verifyPlayerAdded("T");
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
		customPrintln("Answer was corrent!!!!");
		customPrintln("T now has 1 Gold Coins.");
		Mockito.verifyNoMoreInteractions(printStreamMock);
	}
	
	private void verifyPlayerAdded(String playerName) {
		customPrintln("T was added");
	    customPrintln("They are player number 1");
	}


	private void customPrintln(Object string) {
		Mockito.verify(printStreamMock).println(string);
	}


	@Test
	public void shouldWinAfterBadAndGoodAnswer() throws Exception {
		Game game = createGame();
		
		game.add("T");
		
		Assertions.assertThat(game.wrongAnswer()).isTrue();
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}
	
	@Test
	public void shouldRoll() throws Exception {
		Game game = createGame();
		
		game.add("T");
		
		game.roll(0);
		
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}
	
	@Test
	public void shouldRollAfterWrongAnswer() throws Exception {
		Game game = createGame();
		
		game.add("T");

		game.wrongAnswer();
		game.roll(0);
		
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}
	
	@Test
	public void shouldRoll1AfterWrongAnswer() throws Exception {
		Game game = createGame();
		
		game.add("T");

		game.wrongAnswer();
		game.roll(1);
		
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}
	
	@Test
	public void shouldRollLargeAmountAfterWrongAnswer() throws Exception {
		Game game = createGame();
		
		game.add("T");

		game.wrongAnswer();
		game.roll(1001);
		
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}

	private Game createGame() {
		return new Game() {
			@Override
			public void customPrintln(Object obj) {
				System.out.println(obj);
				printStreamMock.println(obj);
			}
		};
	}
	

	@Test
	public void shouldRollLargeAmount() throws Exception {
		Game game = createGame();
		
		game.add("T");

		game.roll(1001);
		
		Assertions.assertThat(game.wasCorrectlyAnswered()).isTrue();
	}
	
}
