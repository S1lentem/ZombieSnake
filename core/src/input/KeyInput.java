package input;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import gameobjects.Modifiable;
import snake.Direction;

public class KeyInput implements Modifiable, Serializable {
	
	private KeyInputable manager;

	public KeyInput() {
		manager = new NormalKeyInput();
	}
	
	@Override
	public void setModification() {
		manager = new ReverseKeyInput();
	}

	@Override
	public void resetModification() {
		manager = new NormalKeyInput();
	}

	public Direction getDirection() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			return manager.getDirection(Input.Keys.UP);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			return manager.getDirection(Input.Keys.DOWN);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			return manager.getDirection(Input.Keys.LEFT);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			return manager.getDirection(Input.Keys.RIGHT);
		}
		return null;
	}

	public String getDescriptionModification() {
		return "Reverse input";
	}
}