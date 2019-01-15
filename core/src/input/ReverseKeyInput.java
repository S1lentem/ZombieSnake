	package input;

import java.io.Serializable;

import com.badlogic.gdx.Input;

import snake.Direction;

public final class ReverseKeyInput implements KeyInputable, Serializable {

	@Override
	public Direction getDirection(int key) {
		if (key == Input.Keys.DOWN) {
			return Direction.Up;
		}
		else if (key == Input.Keys.UP) {
			return Direction.Down;
		}
		else if (key == Input.Keys.LEFT) {
			return Direction.Right;
		}
		else if (key == Input.Keys.RIGHT) {
			return Direction.Left;
		}
		return null;
	}

}
