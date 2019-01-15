package snake;

import java.io.Serializable;

public final class EatTrueFoodCommand extends CommandEat implements Serializable {
	private Snake snake;
	
	public EatTrueFoodCommand(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void excecut() {
		this.snake.addChild();
	}
	
	
}
