package snake;

import java.io.Serializable;

public class EatFalseFoodCommand extends CommandEat implements Serializable {
	private Snake snake;
	
	public EatFalseFoodCommand(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void excecut() {
		snake.removeChild();
	}
}
