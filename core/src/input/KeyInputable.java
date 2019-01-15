package input;

import java.io.Serializable;

import snake.Direction;

public interface KeyInputable extends Serializable {
	Direction getDirection(int key);
}
